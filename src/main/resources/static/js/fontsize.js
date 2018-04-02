
function setCookie(cname, cvalue, exdays) {
    	    var d = new Date();
    	    d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
    	    var expires = "expires="+d.toUTCString();
    	    document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
    	}

    	function getCookie(cname) {
    	    var name = cname + "=";
    	    var ca = document.cookie.split(';');
    	    for(var i = 0; i < ca.length; i++) {
    	        var c = ca[i];
    	        while (c.charAt(0) == ' ') {
    	            c = c.substring(1);
    	        }
    	        if (c.indexOf(name) == 0) {
    	            return c.substring(name.length, c.length);
    	        }
    	    }
    	    return "";
    	}
    	
    	
    	
    	var bigfontsize = getCookie("bigfontsize");
		if (bigfontsize != "")
			$("#bigfontsize")[0].value = bigfontsize;
		else
			$("#bigfontsize")[0].value = "off";
		
		if ($("#bigfontsize")[0].value == "on") { 
			$("#bigfontsize")[0].checked = "true";
			$("body").find("*").each(function () {this.style["font-size"] = "1.6rem"});
		}
		
    	$(document).ready(function () {
    		$("#bigfontsize").on("click", function() {
    			if (this.value == "off") {
    				$("body").find("*").each(function () {this.style["font-size"] = "1.6rem"});
    				setCookie("bigfontsize", "on", 900);
    				this.value = "on";
    			}
    			else {
    				$("body").find("*").each(function () {this.style["font-size"] = ""});
    				setCookie("bigfontsize", "off", 900);
    				this.value = "off";
    			}
    		})
    	})