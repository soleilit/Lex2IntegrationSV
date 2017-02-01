package se.solarplexusit.lexportlet.servlet;


import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import se.solarplexusit.lexportlet.dataobjects.Document;
import se.solarplexusit.lexportlet.service.LexService;
import se.solarplexusit.support.spring.servlet.view.BinaryView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BinaryController extends AbstractController {
    private LexService lexService;

    public BinaryController(LexService lexService) {
        this.lexService = lexService;
    }

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = ServletRequestUtils.getStringParameter(request, "id");
        Document document = lexService.getDocumentFile(id);
        String contentType = "application/" + document.getFile_format(); // TODO Fix!
        return new ModelAndView(new BinaryView(contentType, document.getData()));
    }
}

