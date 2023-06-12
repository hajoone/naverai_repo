package cfr;

import java.io.File;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.ai.MyNaverInform;
import com.example.ai.NaverService;

@Controller
public class FaceController {
	@Autowired
	@Qualifier("faceservice1")
	NaverService service;

	@Autowired
	@Qualifier("faceservice2")
	NaverService service2;

	@RequestMapping("/faceinput")
	public ModelAndView faceinput() {

		File f = new File(MyNaverInform.path);
		String[] filelist = f.list();
		String file_ext[] = { "jpg", "gif", "png", "jfif" };
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
		mv.setViewName("faceinput");
		return mv;
	}

	@RequestMapping("/faceresult")
	public ModelAndView faceresult(String image) {
		String faceresult = service.test(image);
		ModelAndView mv = new ModelAndView();
		mv.addObject("faceresult", faceresult);
		mv.setViewName("faceresult");
		return mv;
	}

	// 안면인식서비스 파일리스트
	@RequestMapping("/faceinput2")
	public ModelAndView faceinput2() {

		File f = new File(MyNaverInform.path);
		String[] filelist = f.list();
		String file_ext[] = { "jpg", "gif", "png", "jfif" };
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
		mv.setViewName("faceinput2");
		return mv;
	}

	@RequestMapping("/faceresult2")
	public ModelAndView faceresult2(String image) {
		String faceresult2 = service2.test(image);
		ModelAndView mv = new ModelAndView();
		mv.addObject("faceresult2", faceresult2);
		//mv.setViewName("faceresult2");//텍스트 성별 나이 감정 얼굴방향 눈코입
		mv.setViewName("faceresult3"); // canvas(이미지 도형) 얼굴사각형 표시
		return mv;
	}
}
