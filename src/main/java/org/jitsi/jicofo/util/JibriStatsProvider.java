package org.jitsi.jicofo.util;

import org.jitsi.jicofo.recording.jibri.*;
import org.osgi.framework.*;

public class JibriStatsProvider extends OsgiServiceProvider<JibriStats>
{
    public JibriStatsProvider(BundleContext bundleContext)
    {
        super(bundleContext, JibriStats.class);
    }
}
