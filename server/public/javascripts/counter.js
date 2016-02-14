
        

        function desableSection(id){
           var fields = document.getElementById(id).getElementsByTagName('*');
                for(var i = 0; i < fields.length; i++){
                fields[i].disabled = true;
                } 
        }
        
        function enableSection(id){
              var fields = document.getElementById(id).getElementsByTagName('*');
                for(var i = 0; i < fields.length; i++){
                fields[i].disabled = false;
                } 
            
        }
             
        var second = 0;
        var minute = 0;
        var hour = 0;
        var isRunning = 0;
        var isStarted = 0;
         
        function initializeClock(id, number){
            var clock = document.getElementById(id);
            var daysSpan = clock.querySelector('.days');
            var hoursSpan = clock.querySelector('.hours');
            var minutesSpan = clock.querySelector('.minutes');
            var secondsSpan = clock.querySelector('.seconds');
           
            hoursSpan.innerHTML = hour;
            minutesSpan.innerHTML = minute;
           
            var timeinterval = setInterval(increment, number);
        }
        
        var i = 1;
        function increment(){
            if(isRunning === 0 || isRunning === -1){
                return;
            }
            second = second + 1;
            if(second > 60){
                second = 0;
                minute = minute + 1;
            }
             
            if(minute > 60){
                minute = 0;
                hour = hour + 1;
            }
             
            var clock = document.getElementById('clockdiv');
             
            var secondsSpan = clock.querySelector('.seconds');
            var hoursSpan = clock.querySelector('.hours');
            var minutesSpan = clock.querySelector('.minutes');
            hoursSpan.innerHTML = hour;
            minutesSpan.innerHTML = minute;
             
        }
        
        
        function startCounter(){
            if(isRunning === -1){
            	return;
            }
            var clock = document.getElementById('clockdiv');
            var start = clock.querySelector('#idBtnStart');
            if(isStarted === 0){
                initializeClock('clockdiv',1000); 
                isStarted = 1;
            }
             
            if(isStarted === 1){
                if(isRunning === 1){
                    isRunning = 0;
                }else{
                    isRunning = 1;
                }
            }
                    
        }
        
        function resetCounter(){
            isRunning = 0;
            minute = 0;
            hour = 0;
             
            var clock = document.getElementById('clockdiv');
            clock.querySelector('.resetButton');
            var hoursSpan = clock.querySelector('.hours');
            var minutesSpan = clock.querySelector('.minutes');
            hoursSpan.innerHTML = 0;
            minutesSpan.innerHTML = 0;
        }
        
        
        function visButtonShow(id){
        	$("#" + id).removeClass('hidden');
        }
        
        function visButtonHide(id){
        	$("#" + id).addClass('hidden');
        }
         
    
        function stopButton(id){
        	desableSection(id);
        	stopCounter();
        	visButtonHide('idBtnPause');
        	visButtonShow('idBtnStart');
        }
        
        function startButton(id){
        	enableSection(id);
        	visButtonHide('idBtnStart');
        	visButtonShow('idBtnPause');
        	startCounter();	
        }
        
        function pauseButton(id){
        	startCounter();
        	visButtonHide('idBtnPause');
        	visButtonShow('idBtnStart');
        	desableSection(id);
        	
        }
        
        function stopCounter(){
            isRunning = 0;
        }
        

