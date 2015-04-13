/*******************************************************************************
Copyright [2015] [Rohit Gupta]

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

*******************************************************************************/
package com.asterix.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;



@org.springframework.stereotype.Controller
@RequestMapping("/api")
public class Controller {

	@Autowired
	Service service;
	
	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String hello(ModelMap model) {
		model.addAttribute("msg", "JCG Hello World!");
		return "helloWorld";
	}
	
	@RequestMapping(value = "/displayMessage/{msg}", method = RequestMethod.GET)
	public String displayMessage(@PathVariable String msg, ModelMap model) {
		model.addAttribute("msg", msg);
		return "helloWorld";
	}
	
	//http://localhost:8080/asterix/api/search?value=d
	
    @RequestMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE, method=RequestMethod.GET)
    public String greeting(@RequestParam(value="value", defaultValue="World") String value, ModelMap model) {
		List<String> values = service.search(value);
		StringBuffer returnValue = new StringBuffer();
		Integer counter = 1;
		
		returnValue.append("<table style=\"width:100%\">");
		 for(String v : values) {
			returnValue.append("<tr>");
			returnValue.append("<td>"+counter+++". </td>");
			returnValue.append("<td>"+v+"<td>");
			returnValue.append("</tr>");
		}
		returnValue.append("</table>");
    	model.addAttribute("msg", returnValue.toString());
		return "helloWorld";
		
    }
}
