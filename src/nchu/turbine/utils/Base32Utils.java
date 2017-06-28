package nchu.turbine.utils;

import java.math.BigInteger;

import org.apache.commons.codec.binary.Base32;

public class Base32Utils {
	public static String biginteger_Encode_Base32(String hex){
		Base32 base32=new Base32();
		BigInteger integer=new BigInteger(hex,16);
		String b32=new String(base32.encode(integer.toByteArray()));
		return b32;
		
	}
}
