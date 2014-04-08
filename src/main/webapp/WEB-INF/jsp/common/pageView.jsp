<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/meta.jsp"%>
<div id=PageSelectorBar>
	<s:if test="page.totalCount > 0">
		第 ${page.pageNo}/${page.pageCount } 页 每页显示：${page.pageSize }条 总记录数：${page.totalCount }条 &nbsp;&nbsp; 
		<s:if test="page.hasPrev()">
			<a href="javascript:gotoPage(1)" title="首页">首页</a>
			<a href="javascript:gotoPage(${page.prePage})">上一页</a>
		</s:if>
		<s:iterator begin="%{page.beginPageNo}" end="%{page.endPageNo}" var="num">
			<s:if test="#num != page.pageNo">
				<!-- 非当前页 -->
				<a href="javascript:gotoPage(${num})">${num}</a>
			</s:if>
			<s:else>${num}</s:else>
		</s:iterator>
		<s:if test="page.hasNext()">
			<a href="javascript:gotoPage(${page.nextPage})">下一页</a>
			<a href="javascript:gotoPage(${page.pageCount})" title="尾页">尾页</a>
		</s:if>
	转到： 
		<select id="pn" onchange="gotoPage(this.value)">
			<s:iterator begin="1" end="%{page.pageCount}" var="num">
				<option value="${num}">${num}</option>
			</s:iterator>
		</select>
	</s:if>
	<s:else>没有结果</s:else>
	<script type="text/javascript">$("#pn").val(${page.pageNo});</script>
</div>
