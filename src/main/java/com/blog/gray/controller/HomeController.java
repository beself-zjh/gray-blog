/** 
 * projectName: gray-blog 
 * fileName: HomeController.java 
 * packageName: com.blog.gray.controller 
 * date: Oct 12, 20211:56:04 PM 
 * copyright(c) 2017-2020 xxx公司
 */
package com.blog.gray.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.blog.gray.annotation.PageView;
import com.blog.gray.domain.RawBlogDO;
import com.blog.gray.factory.ViewModelFactory;
import com.blog.gray.service.ArticleService;
import com.blog.gray.viewmodel.AboutMeViewModel;
import com.blog.gray.viewmodel.ArchivesViewModel;
import com.blog.gray.viewmodel.FooterViewModel;
import com.blog.gray.viewmodel.HeaderViewModel;

/**
 * @title: HomeController.java
 * @package com.blog.gray.controller
 * @description: Home page controller
 * @author: Zjh
 * @date: Oct 12, 2021 1:56:04 PM
 * @version: V1.0
 */
@Controller
public class HomeController {
	
	@Autowired
	private HeaderViewModel headerViewModel;
	
	@Autowired
	private FooterViewModel footerViewModel;
	
	@Autowired
	private AboutMeViewModel aboutMeViewModel;
	
	@Autowired
	private ArchivesViewModel archivesViewModel;
	
	@Autowired
	private ViewModelFactory viewModelFactory;
	
	@Autowired
	private ArticleService articleService;

	@RequestMapping(path = "/home", method = RequestMethod.GET)
	public String homeHandler(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page, 
							  Model model) {
		model.addAttribute("headerViewModel", headerViewModel);
		model.addAttribute("footerViewModel", footerViewModel);
		model.addAttribute("homeViewModel", viewModelFactory.createHomeViewModel(page));
		
		return "html/home";
	}

	@PageView
	@RequestMapping(path = "/blog", method = RequestMethod.GET)
	public String articleHandler(HttpServletRequest request, 
								 @RequestParam(value = "id", required = false, defaultValue = "-1") Integer id,
								 Model model) {
		model.addAttribute("headerViewModel", headerViewModel);
		model.addAttribute("footerViewModel", footerViewModel);
		model.addAttribute("blogViewModel", viewModelFactory.createBlogViewModel(id));

		return "html/blog";
	}
	
	@RequestMapping(path = "/tags", method = RequestMethod.GET)
	public String tagsHandler(@RequestParam(value = "id", required = false, defaultValue = "-1") Integer id,
							  @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
							  Model model) {
		model.addAttribute("headerViewModel", headerViewModel);
		model.addAttribute("footerViewModel", footerViewModel);
		model.addAttribute("tagsViewModel", viewModelFactory.createTagsViewModel(id, page));
		
		return "html/tags";
	}
	
	@RequestMapping(path = "/archives", method = RequestMethod.GET)
	public String archivesHandler(Model model) {
		model.addAttribute("headerViewModel", headerViewModel);
		model.addAttribute("footerViewModel", footerViewModel);
		model.addAttribute("archivesViewModel", archivesViewModel.init());
		
		return "html/archives";
	}
	
	@RequestMapping(path = "/aboutMe", method = RequestMethod.GET)
	public String aboutMeHandler(Model model) {
		model.addAttribute("headerViewModel", headerViewModel);
		model.addAttribute("footerViewModel", footerViewModel);
		model.addAttribute("aboutMeViewModel", aboutMeViewModel);
		
		return "html/aboutMe";
	}
	
	@RequestMapping(path = "/manage", method = RequestMethod.GET)
	public String adminManageHandler(Model model) {

		return "html/admin/admin";
	}
	
	@RequestMapping(path = "/edit", method = RequestMethod.GET)
	public String adminEditHandler(Model model) {
		model.addAttribute("headerViewModel", headerViewModel);
		model.addAttribute("footerViewModel", footerViewModel);
		
		return "html/admin/edit";
	}
	
	@RequestMapping(path = "/admin/upload/image", method = RequestMethod.POST)
	public void uploadImageHandler(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "editormd-image-file", required = true) MultipartFile attach) {
		try {
	        request.setCharacterEncoding( "utf-8" );
	        response.setHeader( "Content-Type" , "text/html" );
	        String rootPath = request.getSession().getServletContext().getRealPath("/resources/upload/");
	        
	        // 文件路径不存在则需要创建文件路径
	        File filePath=new File(rootPath);
	        if(!filePath.exists()){
	            filePath.mkdirs();
	        }

	        // 最终文件名
	        File realFile=new File(rootPath + File.separator + attach.getOriginalFilename());
	        FileUtils.copyInputStreamToFile(attach.getInputStream(), realFile);

	        System.out.println(rootPath + File.separator + attach.getOriginalFilename());
	        // 下面response返回的json格式是editor.md所限制的，规范输出就OK
	        response.getWriter().write( "{\"success\": 1, \"message\":\"successfully upload\",\"url\":\"/resources/upload/" + attach.getOriginalFilename() + "\"}" );
	    } catch (Exception e) {
	        try {
	            response.getWriter().write( "{\"success\":0}" );
	        } catch (IOException e1) {
	            e1.printStackTrace();
	        }
	    }
	}
	
	@RequestMapping(path = "/admin/upload/blog", method = RequestMethod.GET)
	@ResponseBody
	public void uploadBlogHandler(RawBlogDO rawBlog) {
		articleService.save(articleService.createArticle(
				rawBlog.getTitle(), rawBlog.getType(), 
				rawBlog.getContent(), rawBlog.getLabels()
		));
	}
	
	@RequestMapping(path = "/resources/upload/", method = RequestMethod.GET)
	public void getImageHandler() {
		
	}
	
}
