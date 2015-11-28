function envoyerDonneesAjax(data) {
        
        $.ajax({
            type: "post",
            url : "/ajax",
            data : data,
            success : function(a,b) {
                console.log(a);
                return true;
            },
            error : function(a,b,c) {
                console.log("non");
                return false;
            }
        });
        
}