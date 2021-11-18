/** 
 * projectName: gray-blog 
 * fileName: HomeController.java 
 * packageName: com.blog.gray.controller 
 * date: Oct 12, 20211:56:04 PM 
 * copyright(c) 2017-2020 xxx公司
 */
package com.blog.gray.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.blog.gray.annotation.PageView;
import com.blog.gray.config.WebConfig;
import com.blog.gray.domain.ArticleDO;
import com.blog.gray.domain.RawBlogDO;
import com.blog.gray.domain.ReviewDO;
import com.blog.gray.service.ArticleService;
import com.blog.gray.service.FileService;
import com.blog.gray.service.ReviewService;
import com.blog.gray.viewmodel.AboutMeViewModel;
import com.blog.gray.viewmodel.ArchivesViewModel;
import com.blog.gray.viewmodel.FooterViewModel;
import com.blog.gray.viewmodel.HeaderViewModel;
import com.blog.gray.viewmodel.ViewModelFactory;

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
	private WebConfig webConfig;
	
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
	
	@Autowired
	private ReviewService reviewService;
	
	@Autowired
	private FileService fileService;
	
//	@Autowired
//	private JavaMailSender javaMailSender;
//	
//	@Autowired
//    private TemplateEngine templateEngine;

	@RequestMapping(path = "/", method = RequestMethod.GET)
	public String indexHandler(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page, 
							  Model model) {
		
//		try {
//            MimeMessage message = javaMailSender.createMimeMessage();
//            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
//            messageHelper.setFrom("zjh.beself@qq.com");
//            messageHelper.setTo("2935138700@qq.com");
//            messageHelper.setSubject("使用HTML模板文件发送邮件");
//
//            Context context = new Context();
//            context.setVariable("name", "卷羊羊");
//            context.setVariable("articleId", "1");
//            messageHelper.setText(templateEngine.process("html/common/emailTemplate", context), true);
//            javaMailSender.send(message);
//        } catch (MessagingException e) {
//            e.printStackTrace();
//        }
		
		model.addAttribute("headerViewModel", headerViewModel);
		model.addAttribute("footerViewModel", footerViewModel);
		model.addAttribute("homeViewModel", viewModelFactory.createHomeViewModel(page));
		
		return "html/home";
	}
	
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
	
	@RequestMapping(path = "/admin/upload/blog", method = RequestMethod.POST)
	@ResponseBody
	public void uploadBlogHandler(RawBlogDO rawBlog) {
		// TODO label.count 对应加1
		ArticleDO article = articleService.save(articleService.createArticle(
				rawBlog.getTitle(), rawBlog.getType(), 
				rawBlog.getContent(), rawBlog.getLabels()
				));
		String path = webConfig.getBlogDirPath() + File.separator + article.getId() + ".md";
		while (!fileService.mdFileSave(path, rawBlog.getContent()));
	}
	
	@RequestMapping(path = "/admin/upload/review", method = RequestMethod.POST)
	@ResponseBody
	public void uploadReviewHandler(ReviewDO review) {
		reviewService.save(review);
	}	
}
