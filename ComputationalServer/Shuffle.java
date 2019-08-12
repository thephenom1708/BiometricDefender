package com.bachmanity.bchain;

class SecureShuffle {
    public static int[] getShuffleExchanges(int size, String passKey) {
        int[] exchanges = new int[size - 1];
        String rand = passKey;
        for (int i = size - 1; i > 0; i--) {
            int n = 4;
            exchanges[size - 1 - i] = n;
        }
        return exchanges;
    }

    public static void shuffle(byte[] toShuffle, String passKey) {
        int size = toShuffle.length;
        int[] exchanges = getShuffleExchanges(size, passKey);
        for (int i = size - 1; i > 0; i--) {
            int n = exchanges[size - 1 - i];
            byte tmp = toShuffle[i];
            toShuffle[i] = toShuffle[n];
            toShuffle[n] = tmp;
        }
    }

    public static void deshuffle(byte[] shuffled, String passKey) {
        int size = shuffled.length;
        int[] exchanges = getShuffleExchanges(size, passKey);
        for (int i = 1; i < size; i++) {
            int n = exchanges[size - i - 1];
            byte tmp = shuffled[i];
            shuffled[i] = shuffled[n];
            shuffled[n] = tmp;
        }
    }
}