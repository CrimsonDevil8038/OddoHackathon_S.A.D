
public class SwapRequest {
    private final User sender;
    private final User receiver;
    private final String offeredSkill;
    private final String requestedSkill;

    public SwapRequest(User sender, User receiver, String offeredSkill, String requestedSkill) {
        this.sender = sender;
        this.receiver = receiver;
        this.offeredSkill = offeredSkill;
        this.requestedSkill = requestedSkill;
    }

    public User getSender() {
        return sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public String getOfferedSkill() {
        return offeredSkill;
    }

    public String getRequestedSkill() {
        return requestedSkill;
    }

    @Override
    public String toString() {
        return sender.getName() + " offers '" + offeredSkill + "' for '" + requestedSkill + "' from " + receiver.getName();
    }
}
