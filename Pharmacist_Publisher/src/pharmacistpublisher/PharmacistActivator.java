package pharmacistpublisher;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class PharmacistActivator implements BundleActivator {

	ServiceRegistration publishServiceRegistration;

	public void start(BundleContext context) throws Exception {
		System.out.println("Pharmacist Service Strated.");
		PharmacistPublish pharmacist = new PharmacistPublisher_Impl();
		publishServiceRegistration = context.registerService(
				PharmacistPublish.class.getName(),pharmacist,null);
	}

	public void stop(BundleContext context) throws Exception {
		System.out.println("Pharmacist Service Stopped.");
		publishServiceRegistration.unregister();
	}

}
