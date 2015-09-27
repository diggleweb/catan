package client.server;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import shared.communication.user.Register_Input;
import shared.communication.user.Register_Output;

public class RegisterUnitTests 
{
	@BeforeClass
	public static void setUpBeforeClass() throws Exception 
	{
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception 
	{
		return;
	}
		
	private Server server;

	@Before
	public void setUp() throws Exception 
	{
		this.server = new Server();
	}

	@After
	public void tearDown() throws Exception 
	{
		
	}
	
	@Test
	public void testLogin() 
	{
		// test valid input
		Register_Input register_input = new Register_Input("new_user", "new_user");
		Register_Output register_result = server.register(register_input);
		assertEquals(register_result.getResponse(), "Success");
		
		// test invalid input
		Register_Input bad_input = new Register_Input("Sam", "sam");
		Register_Output bad_result = server.register(bad_input);
		assertEquals(bad_result.getResponse(), "Failed to register - someone already has that username.");
	}
}
