$(document).ready(function() {
    $("#七堵區").click(function() {
        loadSights("七堵");
    });
    $("#仁愛區").click(function() {
        loadSights("仁愛");
    });
    $("#中山區").click(function() {
        loadSights("中山");
    });
    $("#中正區").click(function() {
        loadSights("中正");
    });
    $("#安樂區").click(function() {
        loadSights("安樂");
    });
    $("#暖暖區").click(function() {
        loadSights("暖暖");
    });
    $("#信義區").click(function() {
        loadSights("信義");
    });
});
function loadSights(zone){
    $.ajax({
        url :'http://localhost:8081/SightAPI?zone='+zone,
        type:'GET',
        datatype:'json',
        success:function(sights){
            showData(sights);
        },
        error: function(error) {
            console.error(error);
        }

    });
 }
 function showData(sigh){
    var sights = JSON.parse(sigh);
    console.log(sights);
    console.log("length:", sights.length);
    $("#sightsContainer").empty();
    for(let i =0; i<sights.length; i++){
        var sight = sights[i];

        console.log("Sight Name:", sight.sightName);
        console.log("Zone:", sight.zone);

         var card = '<div class="col-md-4 mb-4">'+
                            '<div class="card">'+
                                '<div class="card-header">'+
                                    '<h5 class="mb-0">'+sight.sightName+'</h5>'+
                                '</div>'+
                                '<div class="card-body">'+
                                    '<p> 區域：'+sight.zone+'</p>'+
                                    '<p> 分類：'+sight.category+'</p>'+
                                    '<button type="button" class="btn btn-primary" data-bs-toggle="collapse" data-bs-target="#demo' + i + '">詳細資訊</button>'+  // 不同的 id
                                    '<div id="demo' + i + '" class="collapse">'+
                                        '<br>'+
                                        '<img src="'+sight.photoURL+'" class="card-img-top" alt="圖片失效">'+
                                        '<br>'+
                                        '<div>描述：</div>'+
                                        '<p>'+sight.description+'</p>'+
                                        '<p><a href="https://www.google.com/maps/search/?api=1&query='+sight.address+'" target="_blank">'+
                                            '<button class="btn btn-light">地址</button>'+
                                        '</a>'+sight.address+'</p>'+
                                    '</div>'+
                                '</div>'+
                            '</div>'+
                        '</div>';
        $("#sightsContainer").append(card);
    }
 }