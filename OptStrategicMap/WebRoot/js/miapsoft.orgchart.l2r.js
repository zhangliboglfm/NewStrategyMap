/* miapsoft.regiontree
-- version 1.0 左右方向的树
-- copyright 2017-02-20
-- auther 李杰
v1.0
所需js
es5-shim.js
jquery.js
jquery.mousewheel.js使用缩放引入
*/
(function(jQuery){
	$.fn.orgchart = function(options) {
	    var defaultOptions = {
	      'nodeTitle': 'name',
	      'nodeId': 'id',
	      'toggleSiblingsResp': false,
	      'depth': 999,//展开几层
	      'chartClass': '',
	      'parentNodeSymbol': 'fa-users',
	      'draggable': false,
	      'direction': 't2b',
	      'pan': false,
	      'zoom': false,
	      'click':'',
	      'dblclick':'',
	      'mouseover':'',
	      'mouseout':'',
	      'showIcon':false
	    };
		var opts = $.extend(defaultOptions, options);
		// 创建org-chart
	    var $chartContainer = this;
	    var data = opts.data;
	    var $chart = $('<div>', {
	      'data': { 'options': opts },
	      'class': 'orgchart' + (opts.chartClass !== '' ? ' ' + opts.chartClass : '') + (opts.direction !== 't2b' ? ' ' + opts.direction : ''),
	      'click': function(event) {
	        if (!$(event.target).closest('.node').length) {
	          $chart.find('.node.focused').removeClass('focused');
	        }
	      }
	    });
	    if ($.type(data) === 'object') {
	    	buildHierarchy($chart, opts.ajaxURL ? data : attachRel(data, '00'), 0, opts);
		} else {
		    $.ajax({
		      'url': data,
		      'dataType': 'json',
		      'beforeSend': function () {
		        $chart.append('<i class="fa fa-circle-o-notch fa-spin spinner"></i>');
		      }
		    })
		    .done(function(data, textStatus, jqXHR) {
		      buildHierarchy($chart, opts.ajaxURL ? data : attachRel(data, '00'), 0, opts);
		    })
		    .fail(function(jqXHR, textStatus, errorThrown) {
		      console.log(errorThrown);
		    })
		    .always(function() {
		      $chart.children('.spinner').remove();
		    });
		}
	    $chartContainer.append($chart);
	    if(opts.pan||opts.zoom){
	    	 $chartContainer.css({'overflow':'hidden','text-align': 'center','position':'relative'});
	    }
	    // 允许拖拽
	    if (opts.pan) {
	        $chart.css({'position':'relative'});
	        $chart.on('mousedown',function(e){
	          var $this = $(this);
	          if ($(e.target).closest('.node').length) {
	            $this.data('panning', false);
	            return;
	          } else {
	            $this.css('cursor', 'move').data('panning', true);
	          }
	          var lastX = 0;
	          var lastY = 0;
        	  var position = $this.position();
	          lastX = position.left;
	          lastY = position.top;
	          var startX = e.pageX - lastX;
	          var startY = e.pageY - lastY;

	          $(document).on('mousemove',function(ev) {
	            var newX = ev.pageX - startX;
	            var newY = ev.pageY - startY;
	            $this.css({'left':newX+'px','top':newY+'px'});
	          });
	          $(document).on('mouseup',function() {
	        	  if ($chart.data('panning')) {
	        		  $chart.css('cursor', 'default');
	        		  $(this).off('mousemove');
	        	  }
	          });
	        });
	      }
	      // 允许缩放
	      if (opts.zoom) {
	        $chartContainer.on('mousewheel', function(event, delta, deltaX, deltaY) {
	        	  event.preventDefault();
		          var base = $chart.data("zoom")!=undefined?$chart.data("zoom"):1;
		          var newScale  = base + (deltaY > 0 ? 0.2 : -0.2);
		          newScale = Math.round(newScale*10)/10;
		          if(base==0.2&&deltaY<0){
		        	  $chart.css("zoom",base).data("zoom",base);
		          }else{
		        	  $chart.css("zoom",newScale).data("zoom",newScale);
		          }
	        });
	      }
	      return $chartContainer;
	};
	// recursively build the tree
    function buildHierarchy ($appendTo, nodeData, level, opts, callback) {
	    var $table;
	    // Construct the node
	    var $childNodes = nodeData.children;
	    var hasChildren = $childNodes ? $childNodes.length : false;
	    if (Object.keys(nodeData).length > 1) { // if nodeData has nested
												// structure
	      $table = $('<table>');
	      $appendTo.append($table);
	      $.when(createNode(nodeData, level, opts))
	      .done(function($nodeDiv) {
	    	  if (hasChildren) {
	    		var isHidden = level + 1 >= opts.depth ? ' hidden' : '';
	    		var $tr; 
	    		if (Object.keys(nodeData).length === 1) { // if nodeData is just an array
			        $table = $appendTo;
			    }
	  	    	for (var i=0; i<$childNodes.length*2; i++) {
	  	    		$tr = $('<tr></tr>');
	  	    		if(i%2==1){
	  	    			if(i==$childNodes.length*2-1){
	  	    				$tr.append('<td class="top ' + isHidden + ' lines">&nbsp;</td>');
	  	    			}else{
	  	    				$tr.append('<td class="top left ' + isHidden + ' lines">&nbsp;</td>');
	  	    			}
	  	    		}else{
	  	    			if(i==0){
	  	    				$tr = $nodeDiv.wrap('<tr><td rowspan="' + $childNodes.length * 2 + '"></td></tr>').closest('tr');
		  	  	    		$tr.append('<td class="lines ' + isHidden + '" rowspan="' + $childNodes.length * 2 + '"><div class="down">&nbsp;</div></td><td class="lines bottom ' + isHidden + '">&nbsp;</td>');
	  	    			}else{
	  	    				$tr.append('<td class="lines bottom left ' + isHidden + '">&nbsp;</td>');
	  	    			}
	  	    			var $td = $('<td class="nodes ' + isHidden + '" rowspan="2">');
	  	    			$tr.append($td);
	  	    			buildHierarchy($td, $childNodes[i/2], level + 1, opts, callback);
	  	    		}
	  	    		$table.append($tr);
	   	        }
	    	}else{
	    		$table.append($nodeDiv.wrap('<tr><td></td></tr>').closest('tr'));
	    	}
	        if (callback) {
	          callback();
	        }
	      })
	      .fail(function() {
	        console.log('Failed to creat node');
	      });
	    }
	  }
      function attachRel(data, flags) {
        data.relationship = flags + (data.children && data.children.length > 0 ? 1 : 0);
        if (data.children) {
          data.children.forEach(function(item) {
            attachRel(item, '1' + (data.children.length > 1 ? 1 : 0));
          });
        }
        return data;
      }
      // create node
	  function createNode(nodeData, level, opts) {
		  var dtd = $.Deferred();
		  var content = $.isFunction(opts.nodeContent) ? '<div class="content">' + opts.nodeContent(nodeData) + '</div>' : '<div class="content">' + (nodeData[opts.nodeContent] || '') + '</div>';
		  var nodeTitle = $.isFunction(opts.nodeTitle) ? '<div class="title">' + opts.nodeTitle(nodeData) + '</div>' : '<div class="title">' + nodeData[opts.nodeTitle] || '' + '</div>';
		  var $nodeDiv = $('<div' + (opts.draggable ? ' draggable="true"' : '') + (nodeData[opts.nodeId] ? ' id="' + nodeData[opts.nodeId] + '"' : '') + '>')
	      .addClass('node ' + (nodeData.className || '') +  (level >= opts.depth ? ' slide-up' : ''))
	      .append(nodeTitle)
	      .append(content);
		  if (nodeData.children!=undefined&&opts.showIcon==true) {
			  if (level>=opts.depth-1) {
				  $nodeDiv.append('<i class="edge openEdge">+</i>');
			  } else { 
				  $nodeDiv.append('<i class="edge closeEdge">-</i>');
			  }
		  }
		  //绑定openEdge click事件
		  $nodeDiv.on('click','.openEdge', function(event) {
			  var $that = $(this);
		      var $node = $that.parent();
			  var childrenState = getNodeState($node, 'children');
			  if (childrenState.exist) {
				  var $children = $node.closest('tr').siblings(':last');
				  if ($children.find('.node:visible').is('.slide')) { return; }
				  // hide the descendant nodes of the specified node
				  if (childrenState.visible) {
					  hideDescendants($node);
				  } else { // show the descendants
					  showDescendants($node);
				  }
			  }
		  });
		  //绑定closeEdge click事件
		  $nodeDiv.on('click','.closeEdge', function(event) {
			  var $that = $(this);
		      var $node = $that.parent();
			  var childrenState = getNodeState($node, 'children');
			  if (childrenState.exist) {
				  var $children = $node.closest('tr').siblings(':last');
				  if ($children.find('.node:visible').is('.slide')) { return; }
				  // hide the descendant nodes of the specified node
				  if (childrenState.visible) {
					  hideDescendants($node);
				  } else { // show the descendants
					  showDescendants($node);
				  }
			  }
		  });
		  //绑定click事件
		 /* $nodeDiv.on('click', function(event) {
			  var $node = $(this);
			  $node.closest('.orgchart').find('.focused').removeClass('focused');
			  $node.addClass('focused');
			  if($.isFunction(opts.click)){
				  opts.click(event);
			  }
	      });*/
		  /*//绑定mouseOver事件
		  $nodeDiv.on('mouseover', function(event) {
			  if($.isFunction(opts.mouseover)){
				  opts.mouseover(event);
			  }
			//绑定mouseOut事件
		    $nodeDiv.one('mouseout', function(event) {
		    	if($.isFunction(opts.mouseout)){
				  opts.mouseout(event);
			    }
		    });
		  });
		  
		  //绑定dblclick事件
		  $nodeDiv.on("dblclick",function(event){
			  if($.isFunction(opts.dblclick)){
				  opts.dblclick(event);
			  }
		  });*/
		  
		  dtd.resolve($nodeDiv);
		  return dtd.promise();
	  }
	//节点状态查询
	  function getNodeState($node, relation) {
	    var $target = {};
	    if (relation === 'parent') {
	      $target = $node.closest('table').closest('td').siblings(':first').find('.node');
	    } else if (relation === 'children') {
	      $target = $node.closest('td').siblings();
	    } else {
	      $target = $node.closest('table').parent().siblings();
	    }
	    if ($target.length) {
	      if ($target.is(':visible')) {
	        return {"exist": true, "visible": true};
	      }
	      return {"exist": true, "visible": false};
	    }
	    return {"exist": false, "visible": false};
	  }
	  //隐藏节点
	  function hideDescendants($node) {
		  $node.closest('table').find('.edge').html("+");
		  $node.closest('table').find('td.lines').addClass('hidden');
		  $node.closest('table').find('td.nodes').addClass('hidden');
	  }
	  //显示节点
	  function showDescendants($node) {
		$node.find('.edge').html("-");
		$node.closest('table').children().children("tr").children('td.lines').removeClass('hidden');
		$node.closest('table').children().children("tr").children('td.nodes').removeClass('hidden');
	  }
	  //阻止冒泡
	  function cancelBubble(event){
		  var e=(event)?event:window.event;
		  if(window.event)
		  {
			  event.cancelBubble = true;
		  }else{
			  event.stopPropagation();
		  }
	  }
})( jQuery );
	