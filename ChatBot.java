import java.util.*;

public class ChatBot {

    private static Map<String, List<String>> responses = new HashMap<>();
    private static Map<String, List<String>> intents = new HashMap<>();

    public static void main(String[] args) {
        initBot();

        Scanner scanner = new Scanner(System.in);
        System.out.println("ChatBot: Hello! How can I help you today?");

        while (true) {
            System.out.print("You: ");
            String input = scanner.nextLine().toLowerCase();

            if (input.equals("exit") || input.equals("bye")) {
                System.out.println("ChatBot: Goodbye! Have a nice day.");
                break;
            }

            String intent = getIntent(input);
            String response = getRandomResponse(intent);
            System.out.println("ChatBot: " + response);
        }

        scanner.close();
    }

    private static void initBot() {
        // Define patterns for each intent
        intents.put("greeting", Arrays.asList("hi", "hello", "hey", "good morning", "good evening"));
        intents.put("goodbye", Arrays.asList("bye", "goodbye", "see you", "farewell"));
        intents.put("thanks", Arrays.asList("thanks", "thank you", "thx", "appreciate it"));
        intents.put("name", Arrays.asList("your name", "who are you", "what are you"));

        // Define responses for each intent
        responses.put("greeting", Arrays.asList("Hello!", "Hi there!", "Hey! How can I help you?"));
        responses.put("goodbye", Arrays.asList("Goodbye!", "See you later!", "Take care!"));
        responses.put("thanks", Arrays.asList("You're welcome!", "Anytime!", "No problem!"));
        responses.put("name", Arrays.asList("I'm an AI chatbot created in Java.", "They call me JavaBot!"));
        responses.put("unknown", Arrays.asList("I'm not sure I understand.", "Can you rephrase that?", "Sorry, I don't get it."));
    }

    private static String getIntent(String input) {
        for (Map.Entry<String, List<String>> entry : intents.entrySet()) {
            for (String pattern : entry.getValue()) {
                if (input.contains(pattern)) {
                    return entry.getKey();
                }
            }
        }
        return "unknown";
    }

    private static String getRandomResponse(String intent) {
        List<String> possibleResponses = responses.getOrDefault(intent, responses.get("unknown"));
        return possibleResponses.get(new Random().nextInt(possibleResponses.size()));
    }
}