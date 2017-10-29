	package cn.itcast.maven;
	import org.junit.Test;
	public class TestSdcard {
		@Test
		public void testGetVender(){
			Sdcard sdcard = new Sdcard();
			String verder = sdcard.getVender();
			System.out.println("Éú²úÉÌ:"+verder);		
		}
	}