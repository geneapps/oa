<%@ page contentType="text/html; charset=UTF-8" %>

<script type="text/javascript">

      var statusCode="${ajaxResult.statusCode}";

      var message="${ajaxResult.message}";                         

      var navTabId="${ajaxResult.navTabId}";

 

      var response = {statusCode:statusCode,

                  message:message,

                  navTabId:navTabId

            }

      if(window.parent.donecallback) window.parent.donecallback(response);

</script>

