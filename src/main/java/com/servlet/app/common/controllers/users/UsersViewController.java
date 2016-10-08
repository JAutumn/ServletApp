package com.servlet.app.common.controllers.users;

import static com.servlet.app.common.AppStarter.getJspDir;
import static com.servlet.app.core.PagingParameters.LIMIT;
import static com.servlet.app.core.PagingParameters.PAGE_NUMBER;
import static com.servlet.app.core.SortParameters.SORT;
import static com.servlet.app.core.SortParameters.TYPE;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.servlet.app.common.model.User;
import com.servlet.app.common.services.UserService;
import com.servlet.app.core.Paging;
import com.servlet.app.core.SortTypes;

@WebServlet("/pages/users/*")
public class UsersViewController extends HttpServlet {
    private static final int MAX_PARAMETERS_SIZE = 4;
    private static final int DEFAULT_LIMIT = 5;
    private static final int DEFAULT_PAGE_NUMBER = 1;
    private static final String DEFAULT_SORT_FIELD = "name";
    private static final String DEFAULT_SORT_TYPE = SortTypes.ASC.getValue();

    private UserService userService;

    @Override
    public void init() throws ServletException {
        userService = new UserService();
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User currentUser = (User) req.getSession(false).getAttribute("user");
        Paging paging = getPaging(req.getQueryString());
        List<User> users = userService.getAllUsersExceptOne(currentUser, paging);
        req.setAttribute("users", users);
        req.setAttribute("paging", paging);
        getServletContext().getRequestDispatcher(getJspDir() + "/users/view.jsp").forward(req, resp);
    }

    private Paging getPaging(String queryString) {
        int limit = DEFAULT_LIMIT;
        int pageNumber = DEFAULT_PAGE_NUMBER;
        String sortType = DEFAULT_SORT_TYPE;
        String sortField = DEFAULT_SORT_FIELD;
        if (queryString != null) {
            Map<String, String> parameters = extractParameters(queryString);
            if (parameters.size() > MAX_PARAMETERS_SIZE) {
                throw new IllegalArgumentException("To much query parameters");
            }
            if (parameters.containsKey(SORT.getValue())) {
                sortField = parameters.get(SORT.getValue());
                if (!userService.getColumnsNames().contains(sortField)) {
                    throw new IllegalArgumentException("No such column to sort");
                }
                if (parameters.containsKey(TYPE.getValue())) {
                    sortType = parameters.get(TYPE.getValue());
                    if (!SortTypes.valuesAsStringList().contains(sortType)) {
                        throw new IllegalArgumentException("No such sortType");
                    }
                }
            } else {
                if (parameters.containsKey(TYPE.getValue())) {
                    throw new IllegalArgumentException("Wrong query parameters");
                }
            }
            if (parameters.containsKey(LIMIT.getValue())) {
                String limitParamAsString = parameters.get(LIMIT.getValue());
                if (StringUtils.isNumeric(limitParamAsString)) {
                    Integer limitParam = Integer.valueOf(limitParamAsString);
                    if (limitParam > 0) {
                        limit = Integer.valueOf(limitParamAsString);
                    }
                }
            }
            if (parameters.containsKey(PAGE_NUMBER.getValue())) {
                String pageNumberParamAsString = parameters.get(PAGE_NUMBER.getValue());
                if (StringUtils.isNumeric(pageNumberParamAsString)) {
                    Integer pageNumberParam = Integer.valueOf(pageNumberParamAsString);
                    if (pageNumberParam > 0) {
                        pageNumber =pageNumberParam;
                    }
                }
            }
        }
        return new Paging(userService.countAll() - 1, limit, pageNumber, sortField, sortType);
    }


    private Map<String, String> extractParameters(String queryString) {
        return Arrays.stream(queryString.split("&"))
                     .map(queryPair -> queryPair.split("="))
                     .collect(Collectors.toMap(queryPairAsArray -> queryPairAsArray[0],
                                               queryPairAsArray -> queryPairAsArray[1]));
    }
}
