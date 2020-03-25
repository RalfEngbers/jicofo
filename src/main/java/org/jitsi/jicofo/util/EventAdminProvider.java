package org.jitsi.jicofo.util;

import org.jitsi.eventadmin.*;
import org.osgi.framework.*;

public class EventAdminProvider extends OsgiServiceProvider<EventAdmin>
{
    public EventAdminProvider(BundleContext bundleContext)
    {
        super(bundleContext, EventAdmin.class);
    }
}
