/* miapsoft.regiontree
-- version 1.0
-- copyright 2017-02-11 
-- auther 李杰
v1.0

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
	      'mouseout':''
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
	        $chart.css({'position':'relative','top':'0px','left':'0px'});
	        $chart.on('mousedown',function(e){
	          var $this = $(this);
	          if ($(e.target).closest('.node').length) {
	            $this.data('panning', false);
	            return;
	          } else {
	            $this.css('cursor', 'move').data('panning', true);
	          }
	          var lastTf = $this.css('transform');
	          var lastX = 0;
	          var lastY = 0;
	          if(lastTf != undefined){
	        	  if (lastTf !== 'none') {
	                  var temp = lastTf.split(',');
	                  if (lastTf.indexOf('3d') === -1) {
	                    lastX = parseInt(temp[4]);
	                    lastY = parseInt(temp[5]);
	                  } else {
	                    lastX = parseInt(temp[12]);
	                    lastY = parseInt(temp[13]);
	                  }
	                }
	                var startX = e.pageX - lastX;
	                var startY = e.pageY - lastY;

	                $(document).on('mousemove',function(ev) {
	                  var newX = ev.pageX - startX;
	                  var newY = ev.pageY - startY;
	                  var lastTf = $this.css('transform');
	                  if (lastTf === 'none') {
	                    if (lastTf.indexOf('3d') === -1) {
	                      $this.css('transform', 'matrix(1, 0, 0, 1, ' + newX + ', ' + newY + ')');
	                    } else {
	                      $this.css('transform', 'matrix3d(1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, ' + newX + ', ' + newY + ', 0, 1)');
	                    }
	                  } else {
	                    var matrix = lastTf.split(',');
	                    if (lastTf.indexOf('3d') === -1) {
	                      matrix[4] = ' ' + newX;
	                      matrix[5] = ' ' + newY + ')';
	                    } else {
	                      matrix[12] = ' ' + newX;
	                      matrix[13] = ' ' + newY;
	                    }
	                    $this.css('transform', matrix.join(','));
	                  }
	                });
	          }else{//IE8
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
	          }
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
	          var lastTf = $chart.css('transform');
	          if(lastTf != undefined){
	        	  var newScale  = 1 + (event.originalEvent.deltaY > 0 ? -0.2 : 0.2);
		          if (lastTf === 'none') {
		            $chart.css('transform', 'scale(' + newScale + ',' + newScale + ')');
		          } else {
		            if (lastTf.indexOf('3d') === -1) {
		              $chart.css('transform', lastTf + ' scale(' + newScale + ',' + newScale + ')');
		            } else {
		              $chart.css('transform', lastTf + ' scale3d(' + newScale + ',' + newScale + ', 1)');
		            }
		          }
	          }else{//IE8
	        	  var base = $chart.data("zoom")!=undefined?$chart.data("zoom"):1;
		          var newScale  = base + (deltaY > 0 ? 0.2 : -0.2);
		          if(newScale<0.2){return;}
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
	        $table.append($nodeDiv.wrap('<tr><td' + (hasChildren ? ' colspan="' + $childNodes.length * 2 + '"' : '') + '></td></tr>').closest('tr'));
	        if (callback) {
	          callback();
	        }
	      })
	      .fail(function() {
	        console.log('Failed to creat node');
	      });
	    }
	 // Construct the inferior nodes and connectiong lines
	    if (hasChildren) {
	      if (Object.keys(nodeData).length === 1) { // if nodeData is just an array
	        $table = $appendTo;
	      }
	      var isHidden = level + 1 >= opts.depth ? ' hidden' : '';
	      // draw the line close to parent node
	      $table.append('<tr class="lines' + isHidden + '"><td colspan="' + $childNodes.length * 2 + '"><div class="down"></div></td></tr>');
	      // draw the lines close to children nodes
	      var linesRow = '<tr class="lines' + isHidden + '"><td class="right">&nbsp;</td>';
	      for (var i=1; i<$childNodes.length; i++) {
	        linesRow += '<td class="left top">&nbsp;</td><td class="right top">&nbsp;</td>';
	      }
	      linesRow += '<td class="left">&nbsp;</td></tr>';
	      $table.append(linesRow);
	      // recurse through children nodes
	      var $childNodesRow = $('<tr class="nodes' + isHidden + '">');
	      $table.append($childNodesRow);
	      $.each($childNodes, function() {
	        var $td = $('<td colspan="2">');
	        $childNodesRow.append($td);
	        buildHierarchy($td, this, level + 1, opts, callback);
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
		  if (nodeData.children!=undefined) {
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
		  $nodeDiv.on('click', function(event) {
			  var $node = $(this);
			  $node.closest('.orgchart').find('.focused').removeClass('focused');
			  $node.addClass('focused');
			  /*var childrenState = getNodeState($node, 'children');
			  if (childrenState.exist) {
				  var $children = $node.closest('tr').siblings(':last');
			      if ($children.find('.node:visible').is('.slide')) { return; }
			      // hide the descendant nodes of the specified node
			      if (childrenState.visible) {
			        hideDescendants($node);
			      } else { // show the descendants
			        showDescendants($node);
			      }
			  }*/
			  if($.isFunction(opts.click)){
				  opts.click(event);
			  }
	      });
		  //绑定mouseOver事件
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
		  });
		  
		  dtd.resolve($nodeDiv);
		  return dtd.promise();
	  }
	//节点状态查询
	  function getNodeState($node, relation) {
	    var $target = {};
	    if (relation === 'parent') {
	      $target = $node.closest('table').closest('tr').siblings(':first').find('.node');
	    } else if (relation === 'children') {
	      $target = $node.closest('tr').siblings();
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
	    var $temp = $node.closest('tr').siblings();
	    if ($temp.last().find('.spinner').length) {
	      $node.closest('.orgchart').data('inAjax', false);
	    }
	    var $visibleNodes = $temp.last().find('.node:visible');
	    $node.find('.edge').html("+");
	    $visibleNodes.find('.edge').html("+");
	    var $lines = $visibleNodes.closest('table').closest('tr').prevAll('.lines').css('visibility', 'hidden');
	    $lines.removeAttr('style').addClass('hidden').siblings('.nodes').addClass('hidden');
	  }
	  //显示节点
	  function showDescendants($node) {
		$node.find('.edge').html("-");
	    var $descendants = $node.closest('tr').siblings().removeClass('hidden')
	      .eq(2).children().find('tr:first').find('.node:visible');
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
	