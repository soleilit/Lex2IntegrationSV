package se.solarplexusit.support.spring.servlet.view;

import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.View;

/**
 * View for displaying byte data
 * 
 * @author William Sporrong
 */
public class BinaryView implements View {
    private final String contentType;
    private byte[] data;

    public BinaryView(String contentType, byte[] data) {
        this.contentType = contentType;
        this.data = data;
    }

    public String getContentType() {
        return contentType;
    }

    public void render(Map map, HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType(contentType);
        response.setContentLength(data.length);
        ServletOutputStream out = response.getOutputStream();
        out.write(data);
        out.flush();
    }
}
