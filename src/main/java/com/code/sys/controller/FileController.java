package com.code.sys.controller;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.code.sys.constant.SysConstant;
import com.code.sys.entity.Book;
import com.code.sys.service.BookService;
import com.code.sys.utils.AppFileUtils;
import com.code.sys.utils.DataGridView;
import com.code.sys.utils.RandomUtils;
import com.code.sys.utils.ResultObj;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 文件上传下载的控制器
 *
 */
@Controller
@RequestMapping("file")
public class FileController {
	@Resource
	private BookService bookService;

	/**
	 * 添加
	 *
	 * @throws IOException
	 * @throws IllegalStateException
	 */
	@RequestMapping("uploadFile")
	@ResponseBody
	public DataGridView uploadFile(MultipartFile mf, HttpServletRequest request) throws IllegalStateException, IOException {
		// 将文件上传到的父目录
//		String parentPath = AppFileUtils.PATH;
//		String parentPath = "/resources/images/";
		String parentPath = request.getSession().getServletContext().getRealPath("resources/images/");
		System.out.println("parentPath："+parentPath);
		// 得到当前日期作为文件夹名称
		String dirName = RandomUtils.getCurrentDateForString();
		// 构造文件夹对象
		File dirFile = new File(parentPath, dirName);
		if (!dirFile.exists()) {
			System.out.println("不存在");
			dirFile.mkdirs();// 创建文件夹
		}
		// 得到文件原名
		String oldName = mf.getOriginalFilename();
		// 根据文件原名得到新名
		String newName = RandomUtils.createFileNameUseTime(oldName, "");
		// 上传文件在目标文件夹中的完整路径
		File dest = new File(dirFile, newName);
		// 将上传的文件内容传输到目标文件dest中，实现文件上传操作。
		mf.transferTo(dest);

		System.out.println(dest.getAbsolutePath());

		Map<String,Object> map = new HashMap<>();
		map.put("src", dirName+"/"+newName);
		return new DataGridView(map);

	}
	/**
	 * 不下载只显示
	 */
	@RequestMapping("downloadShowFile")
	public ResponseEntity<Object> downloadShowFile(String path, HttpServletResponse response,HttpServletRequest request) {
		return AppFileUtils.downloadFile(request,response, path, "");
	}

	/**
	 * 下载图片
	 * @param path
	 * @param response
	 * @return
	 */
	@RequestMapping("downloadFile")
	public ResponseEntity<Object> downloadFile(String path, HttpServletResponse response,HttpServletRequest request) {
		String oldName="";
		return AppFileUtils.downloadFile(request, response, path, oldName);
	}

	/**
	 * 导入excel
	 * @param file
	 * @return
	 */
	@PostMapping("/importExcel")
	@ResponseBody
	public ResultObj importExcel(MultipartFile file){
		try {
			// 读取文件流
			ExcelReader reader = ExcelUtil.getReader(file.getInputStream());
			// 将excel的列映射到实实体类对应的字段
			List<Book> books = reader.readAll(Book.class);
			bookService.saveBatch(books);
			//返回影响的记录数
			return new ResultObj(0, "导入成功");
		} catch (IOException e) {
			e.printStackTrace();
			return new ResultObj(-1, "导入失败");
		}
	}
}