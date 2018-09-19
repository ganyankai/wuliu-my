package com.zry.framework.service;/**
 * Created by zry on 2018/9/18.
 */

import com.zry.framework.domain.Idcard;
import com.zry.framework.dto.IdcardDto;
import com.zry.framework.dto.IdcardExportDto;
import com.zry.framework.dto.ResponseDto;
import com.zry.framework.enums.CheckStatus;
import com.zry.framework.repository.IdcardRepository;
import com.zry.framework.util.ExcelUtils;
import com.zry.framework.util.PatternUtil;
import com.zrytech.framework.base.entity.ServerResponse;
import com.zrytech.framework.base.util.BeanUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Id;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @program: parent
 * @description:
 * @author: lw
 * @create: 2018-09-18 10:44
 **/
@Slf4j
@Service
public class IdcardService {
    @Autowired
    private IdcardRepository idcardRepository;

    public ServerResponse checkIdCard() {
        ServerResponse response = ServerResponse.success();
        List<Idcard> idcards = getList();
        int errCount = 0;
        List<Idcard> idcardSave=new ArrayList<>();
        for (Idcard idcard : idcards) {
            ResponseDto dto = IdcardCheck.checkByThirdApi(idcard);
            if(dto==null){
                idcard.setCheckStatus(CheckStatus.error.getShortValue());
                idcard.setError(dto.getMsg());
                errCount++;
            } else if (CheckStatus.through.getStringValue().equals(dto.getCode())) {
                idcard.setCheckStatus(CheckStatus.through.getShortValue());
            } else {
                idcard.setCheckStatus(CheckStatus.failure.getShortValue());
                idcard.setError(dto.getMsg());
                errCount++;
            }

            idcardSave.add(idcard);
            log.debug(dto.getResultMsg());
            if(idcardSave.size()>5) {
                System.out.println("请求数达到5保存");
                idcardRepository.save(idcardSave);
                idcardSave.clear();
                System.out.println("保存成功");
            }

            if (errCount > 100) {
                response = ServerResponse.fail();
                System.out.println("错误数达到100中断");
                break;
            }
        }

        if(idcardSave.size()>0) {
            idcardRepository.save(idcardSave);
        }

        return response;
    }

    public ServerResponse excelImport(MultipartFile excelFile) {
        try {
            InputStream inputStream = excelFile.getInputStream();
            List<IdcardDto> idcardDtos = ExcelUtils.resolution(inputStream, IdcardDto.class);

            List<Idcard> idcardList = new ArrayList<>();
            for (IdcardDto idcardDto : idcardDtos) {
                idcardDto.setBirthday(getBirthday(idcardDto.getIdcard()));
                idcardDto.setCheckStatus(CheckStatus.nocheck.getShortValue());

                Boolean idCardChecked = PatternUtil.checkIdCard(idcardDto.getIdcard());
                if (!idCardChecked) {
                    idcardDto.setCheckStatus(CheckStatus.failure.getShortValue());
                    idcardDto.setError("身份证号码格式错误");
                }

                String name = getChineseName(idcardDto.getName());
                if (StringUtils.isBlank(name)) {
                    idcardDto.setCheckStatus(CheckStatus.failure.getShortValue());
                    idcardDto.setError("姓名格式错误");
                }
                idcardDto.setName(name);

                Idcard idcard = BeanUtil.copy(idcardDto, Idcard.class);
                idcardList.add(idcard);
            }
            if (idcardList.size() > 0) {
                idcardRepository.save(idcardList);
            }
        } catch (Exception ex) {
            log.debug(ex.getMessage());
        }
        return ServerResponse.success();
    }

    public void excelExport(HttpServletResponse response) {
        try {
            List<Idcard> idcards = idcardRepository.findAll();
            idcards.forEach(idcard -> {
                IdcardExportDto dto = BeanUtil.copy(idcard, IdcardExportDto.class);
                if (idcard.getCheckStatus() == CheckStatus.through.getValue()) {
                    dto.setCheckStatus(CheckStatus.through.getName());
                } else if (idcard.getCheckStatus() == CheckStatus.failure.getValue()) {
                    dto.setCheckStatus(CheckStatus.failure.getName());
                } else if (idcard.getCheckStatus() == CheckStatus.nocheck.getValue()) {
                    dto.setCheckStatus(CheckStatus.nocheck.getName());
                }
            });
            XSSFWorkbook wk = ExcelUtils.export(idcards, IdcardExportDto.class);
            OutputStream out = response.getOutputStream();
            response.reset();
            response.setHeader("Content-disposition", "attachment; filename=" + URLEncoder.encode("export.xlsx", "utf-8"));
            response.setContentType("application/vnd.ms-excel");
            wk.write(out);
            out.close();
        } catch (Exception ex) {
            log.debug(ex.getMessage());
        }
    }

    private String getChineseName(String name) {
        String pattern = "[\\u4E00-\\u9FA5]{2,4}";
        Pattern regex = Pattern.compile(pattern);
        Matcher matcher = regex.matcher(name);
        while (matcher.find()) {
            String group = matcher.group(0);
            return group;
        }
        return null;
    }

    private String getBirthday(String idCard) {
        String pattern = "((18|19|([20]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31))";
        Pattern regex = Pattern.compile(pattern);
        Matcher matcher = regex.matcher(idCard);
        while (matcher.find()) {
            String group = matcher.group(0);
            return group;
        }
        return null;
    }

    private List<Idcard> getList() {
        Idcard idcard = new Idcard();
        idcard.setCheckStatus(CheckStatus.nocheck.getShortValue());
        Example example = Example.of(idcard);
        List<Idcard> idcards = idcardRepository.findAll(example);
        return idcards;
    }

}
