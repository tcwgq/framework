package com.tcwgq.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.tcwgq.dao.UserDao;
import com.tcwgq.domain.User;
import com.tcwgq.form.UserForm;

public class UserManageAction extends DispatchAction {
	@Resource
	private UserDao userDao;

	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		UserForm userForm = (UserForm) form;
		Integer id = userForm.getId();
		String name = userForm.getName();
		userDao.save(new User(id, name));
		request.setAttribute("message", "添加成功");
		return mapping.findForward("message");
	}

}
