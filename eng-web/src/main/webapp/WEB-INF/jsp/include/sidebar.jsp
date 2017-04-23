<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="sidebar-nav">
	<ul>
		<c:forEach items="${menu}" var="menuItem">
			<li>
				<a href="javascript:void(0);" data-target=".menu-${menuItem.id}" class="nav-header collapsed" data-toggle="collapse"><i class="${menuItem.icon}"></i> ${menuItem.name}<i class="fa fa-collapse"></i></a>
			</li>
			<li>
				<ul class="menu-${menuItem.id} nav nav-list collapse">
				<c:forEach items="${menuItem.children}" var="subMenu">
					<li>
						<a href="${subMenu.url}" target="mainFrame"><span class="${subMenu.icon}"></span> ${subMenu.name}</a>
					</li>
				</c:forEach>
				</ul>
			</li>
		</c:forEach>
	</ul>
</div>
