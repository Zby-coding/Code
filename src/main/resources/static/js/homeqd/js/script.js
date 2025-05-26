SerMax()//搜索
Nav('#nav')//导航
mobideMenu()// 移动端主导航
wechat()//微信
pageSelect()//底部表单下拉列表
//搜索点击弹出效果
function SerMax(){
    $('.searchBtn').click(function(){
    	$('#ser').fadeToggle(500);
    	$('.header').toggleClass('current');
		$('.serWrap').toggleClass('on');
		
		if($('.serWrap').hasClass('on')){
			$('.serWrap.on').animate({height:'79px'});
			$('#ser').css('display','block')
			$('body').css('overflow','hidden');
			$('.black_bg01').css('display','block');
			}else{
				$('.serWrap').animate({height:'0'});
				$('#ser').css('display','none')
				$('.black_bg01').css('display','none')
				$('body').css('overflow','auto');
				}
    })
	$('.black_bg01').click(function(){
		$('.header').toggleClass('current');
		$('.serWrap').toggleClass('on');
		if($('.serWrap').hasClass('on')){
			$('.serWrap.on').animate({height:'79px'});
			$('body').css('overflow','hidden');
			$('#ser').css('display','block')
			}else{
				$('.serWrap').animate({height:'0'});
				$('body').css('overflow','auto');
				$('#ser').css('display','none')
				}
		$(this).fadeToggle(100)
		})
}

//下拉菜单 例调用：Nav('#nav');
function Nav(id){
	var oNav = $(id);
	var aLi = oNav.find('li');
	
	aLi.hover(function (){
        $(this).addClass('on');
       $(this).find('.subNav').addClass('flipInY');
       //$(this).find('.subNav').removClass('flipOutY');
        //$(this).css('z-index','9999');
	},function (){
        $(this).removeClass('on');
        $(this).find('.subNav').removeClass('flipInY');
       //$(this).find('.subNav').addClass('flipOutY');
        //$(this).css('z-index','999');
	})	
};
//移动端主导航 
function mobideMenu(){
	$(".mobile-inner-header .mobile-inner-header-icon").click(function(){
	  	$(this).toggleClass("mobile-inner-header-icon-click mobile-inner-header-icon-out");
	  	$(this).toggleClass('iconfont')
	  	$(this).toggleClass('iconzhankai')
	  	$(".mobile-inner-nav").slideToggle(250);
	  	// if($(this).hasClass('mobile-inner-header-icon-click')){
	  	// 	$(this).html('&times;')
	  	// }else{
	  	// 	$(this).html('+')
	  	// }
	  });
	  $(".mobile-inner-nav li").each(function( index ) {
	  	$( this ).css({'animation-delay': (index/20)+'s'});
	  });
	  $('.mobile-inner-nav li strong').click(function(){
	  	$(this).next('dl').slideToggle(500);
	  	$(this).toggleClass('on');
	  	if($(this).hasClass('on')){
	  		$(this).html("&times;")
	  	}else{
	  		$(this).html("+")
	  	}
	  })




}

// function n2(){
// 	$('.systemList').find('li').each(function(e){
// 		if(e%2==1){
// 			$(this).addClass('n2')
// 		}
// 	})
// }
//移动端顶部点击弹出下拉菜单
function Menu(menu,main){
	$(menu).each(function(e){
		var $this = $(this)
		$this.click(function(){
		  	$(this).toggleClass("mobile-inner-header-icon-click mobile-inner-header-icon-out");
		  	$(this).parent().next(".sub_navm").slideToggle(250);
			$(this).parent().next('.sub_navm').find('.phone_toggle').click(function(){
				$(this).next('dl').slideToggle(500);
				$(this).toggleClass('on');
				if($(this).hasClass('on')){
					$(this).html("&times;")
				}else{
					$(this).html("+")
				}
			})
	  });
	})
		
};
function subLeft(){
	$('.subLeft').find('.active').each(function(){
        if($(this).parent().parent().hasClass('second_nav')){
         $(this).parent().parent().css('display','block');
         $(this).parent().parent().parent().addClass('active')
         $(this).parent().parent().prev('.toggles').html('&times;')

        }
    })
	$('.subLeft').find('.toggles').click(function(){
		$(this).next('.second_nav').slideToggle(500);
		$(this).parent('li').toggleClass('active');
		if($(this).parent('li').hasClass('active')){
				$(this).html("&times;")
			}else{
				$(this).html("+")
			}
		})
	}
/*回到顶部*/
$('.goTop').click(function(){


	$('body,html').stop().animate({scrollTop:0});

	return false;

});
$(window).scroll(function(){
    var s = $(window).scrollTop();
    if( s > 500){
        $(".goTop").fadeIn(100);
    }else{
        $(".goTop").fadeOut(200);
    };
});
function slideToggle(){
	$('.teacherList03').find('dl').each(function(){
		if($(this).hasClass('open')){
			$(this).find('dd').css('display','block');
		}
	})
	$('.teacherList03').find('dl dt span').click(function(){
		$(this).parent().nextAll('dd').slideToggle(500);
		$(this).parent().parent().toggleClass('open');
	})
	
}


$(function () {
   var iWSon = document.documentElement.clientWidth;
	if(iWSon > 998){
		var $windowHeight = $(window).height();
		var bodyHeight = $(document.body).height()
	  	var $headerHeight = $('.header').height()*2;
	    $(window).scroll(function(){
	      var scrollTop = $(window).scrollTop();
	      if(scrollTop >= 101 && bodyHeight > $windowHeight+$headerHeight){
	      	$('.header').addClass('currents');
	      }else{
	      	$('.header').removeClass('currents');
	      	
	      }
	    })
    }
});
function menuToggle(){
	$('.menu_btn').click(function(){
		$(this).next('.system_list').slideToggle(500)
		})
	}



// $(function(){
// 	var sWSon = document.documentElement.clientWidth;
// 	if(sWSon >=1225){
// 		var headerHeight = $('.header').height();
// 		var bannerHeight = $('.subBanner').height();
// 		var footerHeight = $('.footer').height();
// 		var cHeight = document.documentElement.clientHeight;
// 		var subPage_height =cHeight-headerHeight-bannerHeight-footerHeight-114;
// 		$('.subPage').css('min-height',subPage_height+"px");
// 	}
// })
function share(){
	$('.share_btn').click(function(){
		$(this).next('.bdsharebuttonbox').slideToggle(500)
	})
	
}
function wechat(){
	var sWSon = document.documentElement.clientWidth;
	if(sWSon>1020){
		$('.code').hover(function(){
			$(this).find('i').stop().slideToggle(300)
		})
	}else{
		$('.code').click(function(event){
			$(this).find('i').slideToggle(300);
			//取消事件冒泡 
     		event.stopPropagation(); 
		})
		//点击空白处隐藏弹出层，下面为滑动消失效果和淡出消失效果。
		 $(document).click(function(event){
			 var _con = $('.code');  // 设置目标区域
			 if(!_con.is(event.target) && _con.has(event.target).length === 0){ 
				//$('#divTop').slideUp('slow');  //滑动消失
				$(this).find('.code i').slideUp(300);
			 }
		});
	}
	
	
}
//分页下拉框跳转方法
function pageSelect(){
  console.log($('.pageSelect').length)
  if($('.pageSelect').length!=0){
    $('.pageSelect').select2({});
                
    $("#select21").on("change",function(){
      window.location.href=$(this).val()
    })
    $("#select22").on("change",function(){
      var $thval=$(this).val()
      if($thval=="") return false;
      //value 索引从0开始
       $(".camel-list .list").eq($thval).find(".demo-img:first").click();
    })
  }
}
function odd_even(id,odd,even){
	$(id).find("tr").each(function(index,element){
	if(index%2==1)
		$(this).css(odd);
		//$(this).addClass('odd');
	else
		$(this).css(even);
		//$(this).addClass('even');
	});
} 
/*tab切换*/
;(function($){
    $.fn.extend({
        tab: function (options){
            var defaults = {         //默认参数
                ev : 'mouseover',    //默认事件'mouseover','click'
                delay : 100,         //延迟时间
                auto : true,         //是否自动切换 true,false
                speed : 2000,        //自动切换间隔时间(毫秒)
                more : false         //是否有more,false,true
            };
            var options = $.extend(defaults, options);  //用户设置参数覆盖默认参数
            return this.each(function (){
                var o = options;
                var obj = $(this);
                var oTil = obj.find('.til_tab');
                var oBox = obj.find('.tabListBox');
                var oMore = null;
                var iNum = 0;
                var iLen = oTil.length;
                obj.find('.til_tab').eq(0).addClass('on')
                obj.find('.tabListBox').eq(0).css('display','block')
                obj.find('.more_tab').eq(0).css('display','block')
                //鼠标事件绑定
                oTil.bind(o.ev , function (){
                    var _this = this;
                    if(o.ev == 'mouseover' && o.delay){
                        _this.timer = setTimeout(function (){
                            change(_this);
                        },o.delay);
                    }else{
                        change(_this);
                    }; 
                })

                oTil.bind('mouseout',function (){
                    var _this = this;
                    clearTimeout(_this.timer);
                });

                //自动切换效果
                (function autoPlay(){
                    var timer2 = null;
                    if(o.auto){
                        function play(){
                            iNum++;
                            if(iNum >= iLen){
                                iNum =0;
                            };
                            change(oTil.eq(iNum));
                        };
                        timer2 = setInterval(play,o.speed);

                        obj.on('mouseover',function (){
                            clearInterval(timer2);
                        })

                        obj.on('mouseout',function (){
                            timer2 = setInterval(play,o.speed);
                        })
                    };
                })();

                function change(box){
                    iNum = $(box).index();
                    oTil.removeClass('on');
                    oBox.css('display','none');
                    if(o.more){
                        oMore = obj.find('.more_tab');
                        oMore.css('display','none');
                        oMore.eq(iNum).css('display','block');
                    };
                    oTil.eq(iNum).addClass('on');
                    oBox.eq(iNum).css('display','block');
                }
            });
        }
    })
})(jQuery);
//教学名师左右等高
function listHeight(){
	var windowWidth = $(window).width();
	$('.teacherTitle,.teacherTxt').height('auto')
	if(windowWidth>=480){
		$(".btbu-list04 li").each(function(){
		    if( $(this).find('.teacherTitle').outerHeight() >$(this).find('.teacherTxt').outerHeight()){
		     $(this).find('.teacherTxt').outerHeight($(this).find('.teacherTitle').outerHeight())
		    }else{
		       $(this).find('.teacherTitle').outerHeight($(this).find('.teacherTxt').outerHeight())
		    }
		  })
	}
}
//导师资源切换
function listToggle(){
	$('.btbu-list05').eq(0).find('i').toggleClass('iconxiangxiazhankai-xianxingyuankuang iconxiangshangshouqi-xianxingyuankuang');
	$('.btbu-list05').eq(0).find('dd').slideDown(300)
	$('.btbu-list05').eq(0).find('dt').addClass('on')
	$('.btbu-list05').each(function(){
		var $this = $(this);
		$this.find('dt').click(function(){
			$this.siblings().find('dd').slideUp(300);
			$this.siblings().find('dt').removeClass('on');
			$this.siblings().find('dt i').addClass('iconxiangxiazhankai-xianxingyuankuang ')
			$this.siblings().find('dt i').removeClass('iconxiangshangshouqi-xianxingyuankuang')
			$(this).find('i').toggleClass('iconxiangxiazhankai-xianxingyuankuang  iconxiangshangshouqi-xianxingyuankuang')
			if($(this).hasClass('on')){
				$(this).next('dd').slideUp(300);
				$(this).removeClass('on')
			}else{
				$(this).addClass('on');
				$(this).next('dd').slideDown(300);

			}
		})
	})
}