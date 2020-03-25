package org.jitsi.jicofo.recording.jibri;

import org.jitsi.eventadmin.*;

import java.util.*;

/**
 * Events emitted for {@link JibriSession}.
 */
public class JibriSessionEvent extends Event
{
    public static final String FAILED_TO_START
        = "org/jitsi/jicofo/jibri/session/FAILED_TO_START";

    private static final String TYPE_KEY = "TYPE";

    /**
     * Creates new {@link JibriSessionEvent} with the {@link #FAILED_TO_START}
     * topic.
     *
     * @param type - Tells which {@link Type} of Jibri session is the new event
     *               for.
     */
    static public JibriSessionEvent newFailedToStartEvent(Type type)
    {
        Dictionary<String, Object> props =  new Hashtable<>(1);

        props.put(TYPE_KEY, type);

        return new JibriSessionEvent(FAILED_TO_START, props);
    }

    public JibriSessionEvent(String topic, Dictionary properties)
    {
        super(topic, properties);
    }

    /**
     * @return {@code true} if the event comes for a SIP Jibri session or
     * {@code false} otherwise.
     */
    public Type getType()
    {
        Object type = getProperty(TYPE_KEY);

        return type instanceof Type ? (Type) type : null;
    }

    public enum Type {
        SIP_CALL,
        LIVE_STREAMING,
        RECORDING
    }
}
