package se.solarplexusit.lexportlet.portlet;

import org.springframework.validation.BindException;
import org.springframework.web.portlet.mvc.SimpleFormController;
import org.springframework.web.portlet.util.PortletUtils;
import se.solarplexusit.lexportlet.dao.SearchAdviceDao;
import se.solarplexusit.lexportlet.dataobjects.SearchAdvice;
import se.solarplexusit.support.spring.ListWrapper;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EditSearchAdviceController extends SimpleFormController {
    private SearchAdviceDao adviceDao;

    public EditSearchAdviceController(SearchAdviceDao adviceDao) {
        this.adviceDao = adviceDao;
        this.setFormView("edit-search-advice");
        this.setSuccessView("edit-search-advice");
        this.setCommandClass(ListWrapper.class);
        this.setCommandName("adviceList");
        this.setSessionForm(false);
    }

    @Override
    protected Object formBackingObject(PortletRequest portletRequest) throws Exception {
        return new ListWrapper<SearchAdvice>(adviceDao.findAll());
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void onSubmitAction(ActionRequest request, ActionResponse response, Object command, BindException e) throws Exception {
        List<SearchAdvice> adviceList = ((ListWrapper<SearchAdvice>) command).getItems();

        if (PortletUtils.hasSubmitParameter(request, "addAdvice")) {
            SearchAdvice newAdvice = new SearchAdvice();
            adviceList.add(newAdvice);
        } else if (PortletUtils.hasSubmitParameter(request, "deleteSelected")) {
            Map removeParams = PortletUtils.getParametersStartingWith(request, "remove");
            adviceList = removeAdvicesThatMatchParameters(adviceList, removeParams);
        }

        adviceDao.saveAll(adviceList);
        
        // Important since the command object is stored in the session and picked up in the next (render) phase
        ((ListWrapper<SearchAdvice>) command).setItems(adviceList);
    }

    private List<SearchAdvice> removeAdvicesThatMatchParameters(List<SearchAdvice> adviceList, Map removeParams) {
        List<SearchAdvice> updatedAdviceList = new ArrayList<SearchAdvice>();
        for (int i = 0; i < adviceList.size(); i++) {
            if (!removeParams.containsKey(String.valueOf(i))) {
                updatedAdviceList.add(adviceList.get(i));
            }
        }
        return updatedAdviceList;
    }
}
