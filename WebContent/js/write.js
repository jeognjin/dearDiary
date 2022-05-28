$(document).ready(function() {
        $('#summernote').summernote({
            /* 폰트선택 툴바 사용하려면 주석해제 */
            // fontNames: ['Roboto Light', 'Roboto Regular', 'Roboto Bold', 'Apple SD Gothic Neo'],
            // fontNamesIgnoreCheck: ['Apple SD Gothic Neo'],
            placeholder: 'TIP 게시글을 입력해 주세요',
            tabsize: 2,
            height: 400,
            resize: false,
            lang: "ko-KR",
            disableResizeEditor: true,
            toolbar: [
                /* 폰트선택 툴바 사용하려면 주석해제 */
                // ['fontname', ['fontname']],
                ['fontsize', ['fontsize']],
                ['style', ['bold', 'italic', 'underline', 'clear']],
                ['color', ['color']],
                ['table', ['table']],
                ['para', ['paragraph']],
               
                ['view', []]
            ],
        
        });
        /* 초기 셋팅 ( etc -> 게시글 수정 or Default font family ) */
        $('#summernote').summernote('code', "<?php echo $positing_text ?>");
        $('.note-current-fontname').css('font-family','Apple SD Gothic Neo').text('Apple SD Gothic Neo');
        $('.note-editable').css('font-family','Apple SD Gothic Neo');

        $(".note-group-image-url").remove();    //이미지 추가할 때 Image URL 등록 input 삭제 ( 나는 필요없음 )

        $("#submit-btn").click(function(){
            var text = $('#summernote').summernote('code');

        });

        /*
         - 이미지 추가 func
         - ajax && formData realtime img multi upload
        */
        function RealTimeImageUpdate(files, editor) {
            var formData = new FormData();
            var fileArr = Array.prototype.slice.call(files);
            fileArr.forEach(function(f){
                if(f.type.match("image/jpg") || f.type.match("image/jpeg" || f.type.match("image/jpeg"))){
                    alert("JPG, JPEG, PNG 확장자만 업로드 가능합니다.");
                    return;
                }
            });
            for(var xx=0;xx<files.length;xx++){
                formData.append("file[]", files[xx]);
            }

            $.ajax({
                url : "./이미지 받을 백엔드 파일",
                data: formData,
                cache: false,
                contentType: false,
                processData: false,
                enctype	: 'multipart/form-data',
                type: 'POST',
                success : function(result) {

                    //항상 업로드된 파일의 url이 있어야 한다.
                    if(result === -1){
                        alert('이미지 파일이 아닙니다.');
                        return;
                    }
                    var data = JSON.parse(result);
                    for(x=0;x<data.length;x++){
                        var img = $("<img>").attr({src: data[x], width: "100%"});   // Default 100% ( 서비스가 앱이어서 이미지 크기를 100% 설정 - But 수정 가능 )
                        console.log(img);
                        $(editor).summernote('pasteHTML', "<img src='"+data[x]+"' style='width:50%;' />");
                    }
                }
            });
        }
    });