package dw01;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class TestNameApplication extends Application<TestNameConfiguration>
{

  public static void main( final String[] args ) throws Exception
  {
    new TestNameApplication().run( args );
  }

  @Override
  public void initialize( final Bootstrap<TestNameConfiguration> bootstrap )
  {
  }

  @Override
  public void run( final TestNameConfiguration configuration,
                   final Environment environment )
  {
    final BookingsResource resource = new BookingsResource();

    environment.jersey().register( resource );
  }
}
