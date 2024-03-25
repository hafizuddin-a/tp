package seedu.duke;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Optional;

public class Group {
    static Optional<String> currentGroupName = Optional.empty();
    static final HashMap<String, Group> groups = new HashMap<>();
    protected String groupName;
    protected ArrayList<User> users;

    public Group(String groupName) {
        this.groupName = groupName;
        this.users = new ArrayList<>();
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public String getGroupName() {
        return groupName;
    }

    /**
     * Retrieves an existing group by its name or creates a new one if it does not exist.
     * It ensures that a user cannot create or join a new group without exiting their current group.
     *
     * @param groupName The name of the group to get or create.
     * @return The existing or newly created group.
     * @throws IllegalStateException If trying to create or join a new group while already in another group.
     */
    public static Optional<Group> getOrCreateGroup(String groupName) {

        // Check if user is accessing a group they are already in
        if (currentGroupName.isPresent() && currentGroupName.get().equals(groupName)) {
            System.out.println("You are in " + groupName);
            return Optional.ofNullable(groups.get(groupName));
        }

        // Use of Optional to handle non-existing groups in hashmap
        Optional<Group> optionalGroup = Optional.ofNullable(groups.get(groupName));

        // Tracker for existing group
        final boolean[] isNewGroupCreated = {false};

        // If the user is in a different group, prevent them from creating or joining a new group.
        Group group = optionalGroup.orElseGet(() -> {
            if (currentGroupName.isPresent() && !currentGroupName.get().equals(groupName)) {
                throw new IllegalStateException("Please exit the current group '" + currentGroupName + " first.");
            }
            Group newGroup = new Group(groupName);
            groups.put(groupName, newGroup);
            System.out.println(groupName + " created.");
            isNewGroupCreated[0] = true;
            return newGroup;
        });

        // If a new group was created, update currentGroupName to reflect this.
        if (isNewGroupCreated[0]) {
            currentGroupName = Optional.of(groupName);
        }

        System.out.println("You are now in " + groupName);
        return Optional.ofNullable(groups.get(groupName));
    }

    public void addUsers(User user) {
        try {
            for (User u : users) {
                if (u.getName().equals(user.getName())) {
                    throw new Exception("User already exists in group");
                }
            }
            users.add(user);
            System.out.println("Added " + user.getName() + " to " + groupName);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void exitGroup() {
        if (currentGroupName.isPresent()) {
            System.out.println("You have exited " + currentGroupName.get() + ".");
            currentGroupName = Optional.empty();
        } else {
            System.out.println("Please try again.");
        }
        System.out.println("Current group: " + currentGroupName.orElse(null));
    }
}

