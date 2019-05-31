package com.action.system;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.action.BaseAction;
import com.common.utils.Const;
import com.model.BaseModel;
import com.service.BaseService;

/**  
 * 登录验证码
 * @author:wangzhen
 * @version:V1.0
 * 2017年9月5日  
 */
@Controller
public class CaptchaAction  extends BaseAction<BaseService,BaseModel>{
	 	@RequestMapping("/captcha")
	 	@ResponseBody
	    public void captcha(){
			try {
				response.setContentType("image/jpeg");  
				String captcha = drawImg(response.getOutputStream());
				getSession().setAttribute(Const.SESSION_CAPTCHA, captcha);
			} catch (IOException e) {
				e.printStackTrace();
			}
	 		
	    }
	 	
	 	
		private String drawImg(OutputStream output){
			String code = "";
			for(int i=0; i<4; i++){
				code += randomChar();
			}
			int width = 70;
			int height = 25;
			BufferedImage bi = new BufferedImage(width,height,BufferedImage.TYPE_3BYTE_BGR);
			Font font = new Font("Times New Roman",Font.PLAIN,20);
			Graphics2D g = bi.createGraphics();
			g.setFont(font);
			Color color = new Color(66,2,82);
			g.setColor(color);
			g.setBackground(new Color(226,226,240));
			g.clearRect(0, 0, width, height);
			FontRenderContext context = g.getFontRenderContext();
			Rectangle2D bounds = font.getStringBounds(code, context);
			double x = (width - bounds.getWidth()) / 2;
			double y = (height - bounds.getHeight()) / 2;
			double ascent = bounds.getY();
			double baseY = y - ascent;
			g.drawString(code, (int)x, (int)baseY);
			g.dispose();
			try {
				ImageIO.write(bi, "jpg", output);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return code;
		}
		
		private char randomChar(){
			Random r = new Random();
			String s = "ABCDEFGHJKLMNPRSTUVWXYZ0123456789";
			return s.charAt(r.nextInt(s.length()));
		}
	 	

}
