<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<title></title>
	<meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="description" content="">
	<meta name="author" content="">
	<%@include file="/WEB-INF/jsp/ref/core-css.jsp"%>
	<%@include file="/WEB-INF/jsp/ref/plugin-datatable-css.jsp"%>
	</head>
<body class=" theme-black">
	<%@include file="/WEB-INF/jsp/include/nav.jsp"%>
	<%@include file="/WEB-INF/jsp/include/sidebar.jsp"%>
	<div class="content">
		<%@include file="/WEB-INF/jsp/include/header.jsp"%>
		<div class="main-content">
			<div class="table-container">
                                        <div class="table-actions-wrapper">
                                            <span> </span>
                                            <select class="table-group-action-input form-control input-inline input-small input-sm">
                                                <option value="">Select...</option>
                                                <option value="Cancel">Cancel</option>
                                                <option value="Cancel">Hold</option>
                                                <option value="Cancel">On Hold</option>
                                                <option value="Close">Close</option>
                                            </select>
                                            <button class="btn btn-sm green table-group-action-submit">
                                                <i class="fa fa-check"></i> Submit</button>
                                        </div>
                                        <table class="table table-striped table-bordered table-hover table-checkable" id="datatable_ajax">
                                            <thead>
                                                <tr role="row" class="heading">
                                                    <th width="2%">
                                                        <label class="mt-checkbox mt-checkbox-single mt-checkbox-outline">
                                                            <input type="checkbox" class="group-checkable" id="checkAll"/>
                                                            <span></span>
                                                        </label>
                                                    </th>
                                                    <th width="5%"> Record&nbsp;# </th>
                                                    <th width="15%"> Date </th>
                                                    <th width="200"> Customer </th>
                                                    <th width="10%"> Ship&nbsp;To </th>
                                                    <th width="10%"> Price </th>
                                                    <th width="10%"> Amount </th>
                                                    <th width="10%"> Status </th>
                                                    <th width="10%"> Actions </th>
                                                </tr>
                                                <tr role="row" class="filter">
                                                    <td> </td>
                                                    <td>
                                                        <input type="text" class="form-control form-filter input-sm" name="order_id"> </td>
                                                    <td>
                                                        <div class="input-group date date-picker margin-bottom-5" data-date-format="dd/mm/yyyy">
                                                            <input type="text" class="form-control form-filter input-sm" readonly name="order_date_from" placeholder="From">
                                                            <span class="input-group-btn">
                                                                <button class="btn btn-sm default" type="button">
                                                                    <i class="fa fa-calendar"></i>
                                                                </button>
                                                            </span>
                                                        </div>
                                                        <div class="input-group date date-picker" data-date-format="dd/mm/yyyy">
                                                            <input type="text" class="form-control form-filter input-sm" readonly name="order_date_to" placeholder="To">
                                                            <span class="input-group-btn">
                                                                <button class="btn btn-sm default" type="button">
                                                                    <i class="fa fa-calendar"></i>
                                                                </button>
                                                            </span>
                                                        </div>
                                                    </td>
                                                    <td>
                                                        <input type="text" class="form-control form-filter input-sm" name="order_customer_name"> </td>
                                                    <td>
                                                        <input type="text" class="form-control form-filter input-sm" name="order_ship_to"> </td>
                                                    <td>
                                                        <div class="margin-bottom-5">
                                                            <input type="text" class="form-control form-filter input-sm" name="order_price_from" placeholder="From" /> </div>
                                                        <input type="text" class="form-control form-filter input-sm" name="order_price_to" placeholder="To" /> </td>
                                                    <td>
                                                        <div class="margin-bottom-5">
                                                            <input type="text" class="form-control form-filter input-sm margin-bottom-5 clearfix" name="order_quantity_from" placeholder="From" /> </div>
                                                        <input type="text" class="form-control form-filter input-sm" name="order_quantity_to" placeholder="To" /> </td>
                                                    <td>
                                                        <select name="order_status" class="form-control form-filter input-sm">
                                                            <option value="">Select...</option>
                                                            <option value="pending">Pending</option>
                                                            <option value="closed">Closed</option>
                                                            <option value="hold">On Hold</option>
                                                            <option value="fraud">Fraud</option>
                                                        </select>
                                                    </td>
                                                    <td>
                                                        <div class="margin-bottom-5">
                                                            <button class="btn btn-sm green btn-outline filter-submit margin-bottom">
                                                                <i class="fa fa-search"></i> Search</button>
                                                        </div>
                                                        <button class="btn btn-sm red btn-outline filter-cancel">
                                                            <i class="fa fa-times"></i> Reset</button>
                                                    </td>
                                                </tr>
                                            </thead>
                                            <tbody> </tbody>
                                        </table>
                                    </div>
			<%@include file="/WEB-INF/jsp/include/footer.jsp"%>
		</div>
	</div>
	<%@include file="/WEB-INF/jsp/ref/core-js.jsp"%>
	<%@include file="/WEB-INF/jsp/ref/plugin-datatable-js.jsp"%>
	<script type="text/javascript">
		$(function() {
		});
	</script>
</body>
</html>