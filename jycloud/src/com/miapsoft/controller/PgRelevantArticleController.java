/*** <p>Title: </p>
* <p>Description: </p>
* <p>Company: 精益有容（北京）科技有限公司</p> 
* @author 白少华
* @date 2018-8-9
*/
package com.miapsoft.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PgRelevantArticleController {

	@RequestMapping("pgrelevantarticle.do")
	public String pgrelevantarticle(HttpServletRequest request, HttpServletResponse response) throws ServletException, Exception{
		return "pgmag/pgrelevantarticle";
	}
}
