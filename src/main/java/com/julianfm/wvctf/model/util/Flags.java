package com.julianfm.wvctf.model.util;

import lombok.Getter;

@Getter
public enum Flags {

	flag_sf_472fc248735f41d0f484f1942477304eb85a3bbbb361dc262bc3a3397ae35dca("1"),
	flag_so_8d80dd6e4c4449c0fdbcce2f919a82654b169c5f7351193ccd24d725147fab53("2"),
	flag_so_a936c7beb36c21bc4e160c0771e296abb4777d0be6603ce739e0e0d494b2e318("3"),
	flag_di_9db3e2d42abc40b2ac3ae54b7d6e2c528fb22f288402e5d671e6cf8c194413fc("4"),
	flag_di_a6c5023f020fac7b8bb87ae1aad338ea80567057afe1dbf520a294f5cd931334("5"),
	flag_xss_f655f2a8f72c55b96e90ea538fa59e9b34e16f1bb7971027e078d045772fee2e("6"),
	flag_xss_08f0ee3843dd031f48a1fa14045b5faa9c1f38eb21ac941479421da5c99afafa("7"),;
	
	private String description;

	Flags(String description) {
		this.description = description;
	}
	
	
	
}
