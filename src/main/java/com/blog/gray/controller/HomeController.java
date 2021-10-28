/** 
 * projectName: gray-blog 
 * fileName: HomeController.java 
 * packageName: com.blog.gray.controller 
 * date: Oct 12, 20211:56:04 PM 
 * copyright(c) 2017-2020 xxx公司
 */
package com.blog.gray.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.blog.gray.annotation.PageView;
import com.blog.gray.factory.ViewModelFactory;
import com.blog.gray.viewmodel.ArticleViewModel;
import com.blog.gray.viewmodel.FooterViewModel;
import com.blog.gray.viewmodel.NavigationViewModel;

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
	private NavigationViewModel navigationViewModel;

	@Autowired
	private ArticleViewModel articleViewModel;
	
	@Autowired
	private FooterViewModel footerViewModel;
	
	@Autowired
	private ViewModelFactory viewModelFactory;

	@RequestMapping(path = "/home", method = RequestMethod.GET)
	public String homeHandler(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page, 
							  Model model) {
		model.addAttribute("footerViewModel", footerViewModel);
		model.addAttribute("homeViewModel", viewModelFactory.createHomeViewModel(page));
		
		return "html/home";
	}

	@PageView
	@RequestMapping(path = "/blog", method = RequestMethod.GET)
	public String articleHandler(HttpServletRequest request, @RequestParam Integer id, Model model) {
		model.addAttribute("navigationViewModel", navigationViewModel.flush());
		model.addAttribute("articleViewModel", articleViewModel.flush("14771972"));

		return "html/blog";
	}
	
	@RequestMapping(path = "/tags", method = RequestMethod.GET)
	public String tagsHandler(@RequestParam(value = "id", required = false, defaultValue = "-1") Integer id,
							  @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
							  Model model) {
		model.addAttribute("footerViewModel", footerViewModel);
		model.addAttribute("tagsViewModel", viewModelFactory.createTagsViewModel(id, page));
		
		return "html/tags";
	}
	
	@RequestMapping(path = "/types", method = RequestMethod.GET)
	public String typesHandler(Model model) {

		return "html/types";
	}
	
	@RequestMapping(path = "/archives", method = RequestMethod.GET)
	public String archivesHandler(Model model) {

		return "html/archives";
	}
	
	@RequestMapping(path = "/aboutMe", method = RequestMethod.GET)
	public String aboutMeHandler(Model model) {

		return "html/aboutMe";
	}
	
	@RequestMapping(path = "/admin", method = RequestMethod.GET)
	public String adminHandler(Model model) {

		return "html/admin/blogs";
	}
	
	@RequestMapping(path = "/edit", method = RequestMethod.GET)
	public String editHandler(Model model) {

		return "html/admin/blogs_input";
	}
}
