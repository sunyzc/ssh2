function gotoPage(pageNo) {
	// $("#pageNo").val(pageNo);
	// $("#mainForm").submit();
	$(document.forms[0]).append("<input type='hidden' name='page.pageNo' value='" + pageNo + "'/>");
	// document.forms[0].pageNo.value = pageNo; // ָ��ҳ�����
	document.forms[0].submit(); // �ύ��
}

function sort(orderBy, defaultOrder) {
	if ($("#orderBy").val() == orderBy) {
		if ($("#order").val() == "")
			$("#order").val(defaultOrder);
		else if ($("#order").val() == "desc")
			$("#order").val("asc");
		else if ($("#order").val() == "asc")
			$("#order").val("desc");
	} else {
		$("#orderBy").val(orderBy);
		$("#order").val(defaultOrder);
	}
	$("#mainForm").submit();
}

function search() {
	$("#order").val("");
	$("#orderBy").val("");
	$("#pageNo").val("1");
	$("#mainForm").submit();
}