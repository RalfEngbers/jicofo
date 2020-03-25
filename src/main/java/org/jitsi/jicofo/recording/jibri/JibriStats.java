package org.jitsi.jicofo.recording.jibri;

import org.jitsi.eventadmin.*;
import org.jitsi.osgi.*;
import org.jitsi.utils.logging.*;
import org.osgi.framework.*;

public class JibriStats
    extends EventHandlerActivator
{
    /**
     * The class logger used by {@link JibriStats}.
     */
    static private final Logger logger = Logger.getLogger(JibriStats.class);

    private volatile int totalSipCallFailures = 0;

    private volatile int totalLiveStreamingFailures = 0;

    private volatile int totalRecordingFailures = 0;

    public JibriStats()
    {
        super(new String[] { JibriSessionEvent.FAILED_TO_START });
    }

    @Override
    public void start(BundleContext bundleContext)
            throws Exception
    {
        super.start(bundleContext);

        bundleContext.registerService(JibriStats.class, this, null);
    }

    // It's only ever 1 thread writing, so it's fine to do ++ on a volatile
    @SuppressWarnings("NonAtomicOperationOnVolatileField")
    @Override
    public void handleEvent(Event event)
    {
        if (!(event instanceof JibriSessionEvent))
        {
            return;
        }

        JibriSessionEvent jibriSessionEvent = (JibriSessionEvent) event;
        JibriSessionEvent.Type type = jibriSessionEvent.getType();

        if (type == null)
        {
            logger.error("No event type passed for JibriSessionEvent");
            return;
        }

        switch(type)
        {
            case SIP_CALL:
                totalSipCallFailures++;
                break;
            case RECORDING:
                totalRecordingFailures++;
                break;
            case LIVE_STREAMING:
                totalLiveStreamingFailures++;
                break;
            default:
                logger.error("Unhandled JibriSessionEvent.Type: " + type);
                break;
        }
    }

    public int getTotalSipCallFailures()
    {
        return totalSipCallFailures;
    }

    public int getTotalLiveStreamingFailures()
    {
        return totalLiveStreamingFailures;
    }

    public int getTotalRecordingFailures()
    {
        return totalRecordingFailures;
    }
}
