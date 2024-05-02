package com.ruoyi.flow.form.web;


import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.flow.vo.PageVO;
import com.ruoyi.flow.vo.R;
import com.ruoyi.flow.form.entity.FormCtrlEntity;
import com.ruoyi.flow.form.req.FromSaveInfoReq;
import com.ruoyi.flow.form.service.impl.FormCtrlServiceImpl;
import org.apache.xmlbeans.impl.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.misc.BASE64Decoder;

import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;


/**
 * 表单控件Controller
 * @author 刘亚平
 * @version 2022-12-26
 */
@Controller
@RequestMapping(value = "/flow/oa/form/formCtrl")
public class FormCtrlController extends BaseController {

	@Autowired
	private FormCtrlServiceImpl formCtrlService;

	/**
	 * 保存表单信息
	 *
	 * @param
	 * @return
	 * @throws IOException
	 */
	@PostMapping("listData")
	@ResponseBody
	public R<List<FormCtrlEntity>> listData(@RequestBody PageVO<FormCtrlEntity> req)  {
		return formCtrlService.getFormCtrlEntityList(req);

	}

	/**
	 * 删除表单控件接口
	 *
	 * @param
	 * @return
	 * @throws IOException
	 */
	@Log(title = "删除表单控件接口", businessType = BusinessType.DELETE)
	@PostMapping("formCtrldelete")
	@ResponseBody
	public R<String> formCtrldelete(@RequestBody FormCtrlEntity req)  {
		if( formCtrlService.deleteById(req.getCtrlId())){
			return R.newOk("删除成功！");
		}else{
			return R.newError("删除失败！");
		}

	}
	/**
	 * 保存表单控件接口
	 *
	 * @param
	 * @return
	 * @throws IOException
	 */
	@Log(title = "保存表单控件接口", businessType = BusinessType.INSERT)
	@PostMapping("save")
	@ResponseBody
	public R<String> save(@RequestBody FormCtrlEntity req) throws IOException  {
		if(!StringUtils.isNotEmpty( req.getCtrlTitle())){
			return R.newOk("共享组件名称不能为空！");
		}
		if(req.getCtrlImg().contains("data:")) {
			Date d = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String filePath = new File("AttachmentFile\\" + sdf.format(d)).getAbsolutePath();
			UUID uuid = UUID.randomUUID();
			File fileUpload = new File(filePath, uuid + ".png");
			if (fileUpload.exists()) {
				return R.newError("上传失败,上传的文件已存在");
			}
			String uploadfileStr = Base64ToFile(req.getCtrlImg(), fileUpload.getPath());
			if (uploadfileStr.equals("生成文件成功!")) {
				req.setCtrlImg("/flow/oa/formfile/file/download?path=" + URLEncoder.encode("AttachmentFile\\" + sdf.format(d) + "\\" + uuid + ".png", "utf-8"));
				formCtrlService.saveOrUpdate(req);
			} else {
				return R.newError(uploadfileStr);
			}
		}else{
			formCtrlService.saveOrUpdate(req);
		}
  		return R.newOk("保存成功！");
	}
	String Base64ToFile(String base64, String filePath) throws IOException {
		// base64编码字符必须不能包含base64的前缀，否则会报错
		// filePath需要为具体到文件的名称和格式，如111.txt
		// 文件路径需要双斜杠转义，如:  D:\\files\\111.txt
		if (base64 == null && filePath == null) {
			return "生成文件失败，未传入参数!";
		}
		// 判断是否base64是否包含data:image/png;base64等前缀，如果有则去除
		if (base64.contains("data:image/png;base64")) {
			base64 = base64.substring(22);
			System.out.println("包含png"+base64);
		}
		if (base64.contains("data:image/jpeg;base64")) {
			base64 = base64.substring(23);
			System.out.println("包含jpeg"+base64);
		}
		if (base64.contains("data:application/pdf;base64")) {
			base64 = base64.substring(28);
			System.out.println("包含pdf"+base64);
		}
		BASE64Decoder decoder = new BASE64Decoder();
		byte[] bytes = decoder.decodeBuffer(base64);
		for (int i = 0; i<bytes.length; ++i) {
			// 调整异常数据
			if (bytes[i] < 0) {
				bytes[i] +=256;
			}
		}
		OutputStream outputStream = null;
		InputStream inputStream = new ByteArrayInputStream(bytes);
		// 此处判断文件夹是否存在，不存在则创建除文件外的父级文件夹
		File file = new File(filePath);
		if (!file.exists()) {
			System.out.println("上级目录"+file.getParentFile());
			file.getParentFile().mkdirs();
		}
		try {
			// 生成指定格式文件
			outputStream = new FileOutputStream(filePath);
			byte[] buff = new byte[1024];
			int len = 0;
			while ((len = inputStream.read(buff)) != -1) {
				outputStream.write(buff, 0, len);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			outputStream.flush();
			outputStream.close();
		}
		return "生成文件成功!";
	}
	
}