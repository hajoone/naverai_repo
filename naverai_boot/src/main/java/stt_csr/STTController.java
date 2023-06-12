package stt_csr;

import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.ai.MyNaverInform;
import com.example.ai.NaverService;

@Controller
public class STTController {
	@Autowired
	@Qualifier("sttservice")
	NaverService service;

	

	@RequestMapping("/sttinput")
	public ModelAndView sttinput() {

		File f = new File(MyNaverInform.path);
		String[] filelist = f.list();
		String file_ext[] = { "mp3", "m4a", "wav"};
		ArrayList<String> newfilelist = new ArrayList();
		for (String onefile : filelist) {
			String myext = onefile.substring(onefile.lastIndexOf(".") + 1);
			for (String imgext : file_ext) {
				if (myext.equals(imgext)) {
					newfilelist.add(onefile);
					break;
				}
			}
		}

		ModelAndView mv = new ModelAndView();
		mv.addObject("filelist", newfilelist);
		mv.setViewName("sttinput");
		return mv;
	}

	@RequestMapping("/sttresult")
	public ModelAndView sttresult(String image, String lang) throws Exception{
		String sttresult = null;
		if(lang == null) {
			sttresult = service.test(image);//기본언어 Kor
		}
		else {
			sttresult = ((STTServiceImpl)service).test(image, lang);
		}
		ModelAndView mv = new ModelAndView();
		mv.addObject("sttresult", sttresult);
		mv.setViewName("sttresult");
		
		//파일 저장 mp3파일이름 _20230609112022.txt
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String now_string = sdf.format(now);
		String filename = image.substring(0, image.lastIndexOf(".")) + "_" + now_string + ".txt";
		
		FileWriter fw = new FileWriter(MyNaverInform.path + filename, false);
		
		JSONObject jsontext = new JSONObject(sttresult);
		String text = (String)jsontext.get("text");
		fw.write(text);
		fw.close();
		return mv;
	}



	

	
}
