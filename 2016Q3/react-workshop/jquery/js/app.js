var $ = require("jquery");
var form2js = require("form2js");

$(function() {
  $("#submitBtn").click(function() {
    $.ajax({
      url: "http://localhost:3000/react/workshop",
      dataType: 'json',
      contentType: 'application/json; charset=UTF-8',
      type: 'POST',
      data: JSON.stringify(form2js.form2js(this.form, '.', true, null, true)),
      success: function(data, textStatus, jqXHR) {
        $("#form input:not(:button)").each(function(index, element) {
          $(element).val(data[$(element).attr("id")]);
        });
        console.debug(data.message);
      },
      error: function(jqXHR, textStatus, errorThrown) {
        console.error(errorThrown);
      }
    });
  });
});