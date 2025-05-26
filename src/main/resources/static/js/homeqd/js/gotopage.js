function _simple_list_gotopage_fun(totle_page, topage_input_obj, pub_mode) {
	if (!window) {
		return;
	}
	var _totle_num = totle_page;
	var _cur_site_pub_mode = pub_mode;
	if (_cur_site_pub_mode == 2) {
		if (window.totalPages) {
			_totle_num = window.totalPages;
		}
	}
	var _topage_input_value = document.getElementById(topage_input_obj).value;
	var _win_location_url = window.location.href;
	var _topage_num = parseInt(_topage_input_value);
	if (isNaN(_topage_num)) {
		_topage_num = 1;
	} else {
		if (_topage_num > _totle_num) {
			_topage_num = _totle_num;
		} else if (_topage_num <= 0) {
			_topage_num = 1;
		}
		if (_cur_site_pub_mode == 2) {
			if (_topage_num != 1 && _topage_num != _totle_num) {
				_topage_num = _totle_num - _topage_num + 1;
			}
		}
	}
	if (_cur_site_pub_mode == 1) {
		if (_win_location_url != null && _win_location_url != undefined
				&& _win_location_url.indexOf(".jsp") > 0) {
			if (_win_location_url.indexOf("totalpage=") > 0
					&& _win_location_url.indexOf("PAGENUM=") > 0) {
				var _pagenum_index = _win_location_url.indexOf("PAGENUM=") + 8;
				var _first_part_url = _win_location_url.substr(0,
						_pagenum_index);
				var _second_part_url = _win_location_url.substr(_pagenum_index,
						_win_location_url.length);
				var _first_andchar_index = _second_part_url.indexOf("&");
				_second_part_url = _second_part_url.substr(
						_first_andchar_index, _second_part_url.length);
				var _new_win_url = _first_part_url + _topage_num
						+ _second_part_url;
				window.location = _new_win_url;
			} else {
				var _first_part_url = "";
				var _second_part_url = "";
				if (_win_location_url.indexOf("?") > 0) {
					_first_part_url = _win_location_url.substring(0,
							_win_location_url.indexOf("?") + 1);
					_second_part_url = _win_location_url.substring(
							_win_location_url.indexOf("?") + 1,
							_win_location_url.length);
					var _new_win_url = _first_part_url + "totalpage="
							+ _totle_num + "&PAGENUM=" + _topage_num + "&"
							+ _second_part_url;
					window.location = _new_win_url;
				}
			}
		}
	}
	if (_cur_site_pub_mode == 2) {
		if (_win_location_url != null && _win_location_url != undefined
				&& _win_location_url.indexOf(".htm") > 0) {
			var _last_sepactor = _win_location_url.lastIndexOf("/");
			var _first_part_url = _win_location_url.substr(0, _last_sepactor
							+ 1);
			var _second_part_url = _win_location_url.substring(_last_sepactor
							+ 1, _win_location_url.lastIndexOf((".")));
			var _second_part_page = new Number(_second_part_url);
			if (isNaN(_second_part_page)) {
				_first_part_url = _first_part_url + _second_part_url + "/";
			}
			var _new_win_url = "";
			if (_topage_num == 1) {
				_first_part_url = _first_part_url.substr(0, _first_part_url
								.lastIndexOf("/"));
				_new_win_url = _first_part_url + ".htm";
			} else if (_topage_num == _totle_num) {
				_new_win_url = _first_part_url + 1 + ".htm";
			} else {
				_new_win_url = _first_part_url + _topage_num + ".htm";
			}
			window.location = _new_win_url;
		}
	}
}