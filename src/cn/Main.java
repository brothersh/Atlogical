package cn;


import data_structure.Nodes.ListNode;
import data_structure.Nodes.RandomListNode;
import data_structure.Nodes.TreeNode;

import java.util.*;
import java.util.logging.Level;

public class Main {
    public static void main(String[] args) {

    }
}

class Solution {
    //力扣
    public int mincostTickets(int[] days, int[] costs) {
        int[] travelCost = new int[days[days.length-1]+1];
        int dayTicket = costs[0];
        int weekTicket = costs[1];
        int monthTicket = costs[2];
        int day = days[0];
        int countDay = 1;
//        travelCost[0] = 0;
//        travelCost[day] = dayTicket;
        for (int i = day+1;i < travelCost.length;i++){
            if (days[countDay] != i){
                travelCost[i] = travelCost[i-1];
                continue;
            }
            countDay++;
            travelCost[i] = Math.min(
                    Math.min(
                            travelCost[Math.max(0,i-1)]+dayTicket,
                            travelCost[Math.max(0,i-7)]+weekTicket
                    ),
                    travelCost[Math.max(0,i-30)]+monthTicket);
        }
        return travelCost[days[days.length-1]];
    }
    public void sortColors(int[] nums) {
        int i = 0,j = nums.length-1;
        for (int k = 0;k < j;){
            if (nums[k] == 0){
                swap(nums,k,i++);
            }else if (nums[k] == 2){
                swap(nums,k,j++);
                continue;
            }
            k++;
        }
    }

    public int clumsy(int N) {
        if (N < 4)
            return less(N);
        int group = N / 4;
        int rest = N % 4;
        int result = N * (N-1)/(N-2)+(N-3);
        for (int i = group-1;i > 0;i--){
            int tmp = group * i + rest;
            result -= tmp*(tmp-1)/(tmp-2)-(tmp-3);
        }
        return result - less(rest);

    }
    public int leastOpsExpressTarget(int x, int target) {
        List<Integer> list = new ArrayList<>();
        while (target != 0){
            list.add(target % x);
            target /= x;
        }
        int n = list.size();
        int[] addLeast = new int[n];
        int[] subLeast = new int[n];
        addLeast[0] = 2 * list.get(0);
        subLeast[0] = 2 * (x-list.get(0));
        for (int i = 1;i < n;i++){
            addLeast[i] = Math.min(addLeast[i-1]+ i*list.get(i),subLeast[i-1]+i*(list.get(i)+1));
            subLeast[i] = Math.min(addLeast[i-1] + i*(x-list.get(i)),subLeast[i-1]+i*(x-list.get(i)-1));
        }
        return Math.min(addLeast[n-1],subLeast[n-1]+n)-1;
    }
    public int longestMountain(int[] A) {
        if (A.length < 3)
            return 0;
        int max = 0;
        for (int i = 0;i < A.length-1;){
            if (A[i] >= A[i+1]) {
                i++;
                continue;
            }
            int begin = i;
            while (i+1 < A.length && A[i] < A[i+1]) i++;
            if (i == A.length-1) break;
            if (A[i] > A[i+1]){
                while (i+1 < A.length && A[i] > A[i+1]) i++;
                max = Math.max(max,i-begin+1);
            }
        }
        return max;
    }
    public int nthSuperUglyNumber(int n, int[] primes) {
        int[] uglies = new int[n];
        uglies[0] = 1;  //第一个丑数是1
        int[] indexes = new int[primes.length]; //indexes[i] 保存的是“uglies里某一个丑数x，x * primes[i] 刚好大于当前最大丑数min” x在uglies里的索引
        for (int i = 1;i < n;i++){
            int min = Integer.MAX_VALUE;
            for (int j = 0;j < primes.length;j++){
                min = Math.min(min,primes[j]*uglies[indexes[j]]);
            }
            uglies[i] = min;
            for (int j = 0;j < primes.length;j++){
                if (primes[j]*uglies[indexes[j]++] <= min);
            }
        }

        return uglies[n-1];
    }
    public List<Integer> getSuperUglyNumberList(int n,int[] primes){
        List<Integer> res = new ArrayList<>();
        for (int i = 1;i <= n;i++){
            res.add(nthSuperUglyNumber(i,primes));
        }
        return res;
    }
    public int hIndex(int[] citations) {
        int n = citations.length;
        int h = 0;
        int max = n-1,min = 0;
        while (max >= min){
            int mid = (max+min)/2;
            if (n-mid > citations[mid]){
                min = mid+1;
            }else {
                h = Math.max(h,n-mid);
                max = mid-1;
            }
        }
        return h;
    }
    public int minSubArrayLen(int s, int[] nums) {
        if (nums.length == 0)
            return 0;
        int min = Integer.MAX_VALUE;
        int i = 0,j = 0;
        int total = nums[0];
        while (i <= j && j < nums.length){
            if (total >= s){
                min = Math.min(min,j-i+1);
                total-= nums[i++];
            }else {
                if (j+1 < nums.length){
                    j++;
                    total += nums[j];
                }else {
                    break;
                }
            }
        }
        return (min == Integer.MAX_VALUE)?0:min;
    }
    public int findShortestSubArray(int[] nums) {
        Map<Integer,Integer> begins = new TreeMap<>();
        Map<Integer,Integer> counts = new TreeMap<>();
        Map<Integer,Integer> mins = new TreeMap<>();
        int res = Integer.MIN_VALUE;
        int maxCount = 0;
        int maxIndex = 0;
        int min = Integer.MAX_VALUE;
        for (int i = 0;i < nums.length;i++){
            begins.putIfAbsent(nums[i],i);
            counts.put(nums[i],counts.getOrDefault(nums[i],1));
            if (maxCount < counts.get(nums[i])){
                min = i-begins.get(nums[i])+1;
            }
        }
        return min;
    }
    public int maxSubArray(int[] nums) {
        int res = nums[0];
        int sum = 0;
        for (int num: nums){
            if (sum > 0)
                sum += num;
            else
                sum = num;
            res = Math.max(sum,res);
        }
        return res;
    }
    public String decodeString(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0;i < s.length();i++){
            char ch = s.charAt(i);
            if (Character.isLetterOrDigit(ch)||ch == '['){
                stack.push(ch);
            }else if(ch == ']'){
                char c;
                String tmp = "";
                while ((c=stack.pop()) != '['){
                    tmp += c;
                }
                tmp = new StringBuilder(tmp).reverse().toString();
                String num = "";
                while (!stack.empty() && Character.isDigit(stack.peek())){
                    num += stack.pop();
                }
                num = new StringBuilder(num).reverse().toString();
                StringBuffer buffer = new StringBuffer();
                for (int j =0;j < Integer.valueOf(num);j++){
                    buffer.append(tmp);
                }
                tmp = buffer.toString();
                for (int j = 0;j < tmp.length();j++){
                    stack.push(tmp.charAt(j));
                }
            }
        }
        String result = "";
        while (!stack.empty()){
            result += stack.pop();
        }
        return new StringBuilder(result).reverse().toString();
    }
    public int missingNumber(int[] nums) {
        int n = nums.length;
        int sum = 0;
        for (int v: nums){
            sum += v;
        }
        return (n+1)*n/2 - sum;
    }
    public int longestPalindrome(String s) {
        Map<Character,Integer> map = new HashMap<>();
        for (int i = 0;i < s.length();i++){
            map.put(s.charAt(i),map.getOrDefault(s.charAt(i),0)+1);
        }
        int sum = 0;
        boolean flag = false;
        for (char c:map.keySet()){
            int count = map.get(c);
            sum += count;
            if (count % 2 != 0){
                if (!flag) flag = true;
                else sum--;
            }
        }
        return sum;
    }
    public String reverseVowels(String s) {
        StringBuilder builder = new StringBuilder(s);
        int i = -1,j = s.length();
        Set<Character> set = new HashSet<>();
        Character[] chs = {'a','e','i','o','u','A','E','I','O','U'};
        Collections.addAll(set,chs);
        while (true){
            while (!set.contains(s.charAt(++i)) && i < s.length()-1);
            while (!set.contains(s.charAt(--j)) && j > 0);
            if (i >= j) break;
            builder.insert(i,s.charAt(j));
            builder.deleteCharAt(i+1);
            builder.insert(j,s.charAt(i));
            builder.deleteCharAt(j+1);
        }
        return builder.toString();
    }
    public int[] advantageCount(int[] A, int[] B) {
        int n = A.length;
        int[] res = new int[n];
        boolean[] record = new boolean[n];
        int index = 0;
        Arrays.sort(A);
        for (int i =  0;i < n;i++){
            int j = 0;
            while (j < n && (A[j] <= B[i] || record[j])) j++;
            if (j < n){
                res[i] = A[j];
                record[j] = true;
            }else {
                while (record[index]) index++;
                res[i] = A[index];
                record[index] = true;
            }
        }
        return res;
    }
    public String[] findWords(String[] words) {
        String[] keys = {"qwertyuiop","asdfghjkl","zxcvbnm"};
        Map<Character,Integer> map = new HashMap<>();
        for (int i = 0;i < keys.length;i++){
            for (int j = 0;j < keys[i].length();j++){
                char ch = keys[i].charAt(j);
                map.put(ch,i);
                map.put(Character.toUpperCase(ch),i);
            }
        }
        List<String> list = new ArrayList<>();
        for (String s: words){
            int flag = map.get(s.charAt(0));
            boolean should = true;
            for (int i = 1;i < s.length();i++){
                if (flag != map.get(s.charAt(i))) {
                    should = false;
                    break;
                }
            }
            if (should) list.add(s);
        }
        String[] res = new String[list.size()];
        list.toArray(res);
        return res;
    }
    public int minDistance(String word1, String word2) {
        int n = word1.length(),m = word2.length();
        int[][] dp = new int[n+1][m+1];
        for (int i = 1;i < n;i++){
            for (int j = 1;j < m;j++){
                if (word1.charAt(i) == word2.charAt(j)){
                    dp[i][j] = dp[i-1][j-1] + 1;
                }else {
                    dp[i][j] = Math.max(dp[i][j-1],dp[i-1][j]);
                }
            }
        }
        return n+m-2*dp[n-1][m-1];
    }


    //牛客
    public int NumberOf1(int n) {
        StringBuilder builder = new StringBuilder(Integer.toBinaryString(n));
        int index;
        int res = 0;
        while ((index=builder.indexOf("1")) != -1){
            res++;
            builder.deleteCharAt(index);
        }
        return res;
    }
    public double Power(double base, int exponent) {
        return Math.pow(base,exponent);
    }
    public void reOrderArray(int [] array) {
        boolean flag;
        do {
            flag = false;
            for (int i = 0;i < array.length-1;i++){
                if (array[i]%2 == 0 || array[i+1]%2==1){
                    flag = true;
                    swap(array,i,i+1);
                }
            }
        }while (flag);
    }
    public ListNode FindKthToTail(ListNode head,int k) {
        ListNode p = head;
        int len = 0;
        while (p != null){
            len++;
            p = p.next;
        }
        p = head;
        for (int i = 1;i <= len-k;i++) p = p.next;
        return p.next;
    }
    public ListNode ReverseList(ListNode head) {
        if (head == null || head.next == null)
            return null;
        ListNode p = head;
        ListNode f = null;
        ListNode c = p.next;
        while (c != null){
            p.next = f;
            f = p;
            p = c;
            c = c.next;
        }
        p.next = f;
        return p;
    }
    public ListNode Merge(ListNode list1, ListNode list2) {
        ListNode p1 = list1;
        ListNode p2 = list2;
        if (p1 == null) return p2;
        else if (p2 == null)return p1;

        while (p1.next != null && p2 != null){
            if (p1.next.val > p2.val){
                ListNode tmp = p2.next;
                p2.next = p1.next;
                p1.next = p2;
                p2 = tmp;
            }
            p1 = p1.next;
        }
        p1.next = p2;
        return list1;
    }
    public boolean HasSubtree(TreeNode root1,TreeNode root2) {
        if (root1 == null || root2 == null) return false;
        return isSubTree(root1,root2) || isSubTree(root2,root1);
    }
    public void Mirror(TreeNode root) {
        if (root != null){
            TreeNode tmp = root.left;
            root.left = root.right;
            root.right = tmp;
            Mirror(root.right);
            Mirror(root.left);
        }
    }
    public ArrayList<Integer> printMatrix(int [][] matrix) {
        int n = matrix.length,m = matrix[0].length;
        isVisited = new boolean[n][m];
        ArrayList<Integer> res = new ArrayList<>();
        dfs(matrix,0,0,0,res);
        return res;
    }
    public boolean IsPopOrder(int [] pushA,int [] popA) {
        int indexPush = 1,indexPop = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(pushA[0]);
        while (indexPop < popA.length){
            if (stack.peek() == popA[indexPop]){
                stack.pop();
                indexPop++;
            }else {
                if (indexPush == pushA.length)
                    return false;
                stack.push(pushA[indexPush++]);
            }
        }
        return true;
    }
    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        LinkedList<TreeNode> queue = new LinkedList<>();
        ArrayList<Integer> res = new ArrayList<>();
        TreeNode p;
        queue.addLast(root);
        while (!queue.isEmpty()){
            p = queue.poll();
            res.add(p.val);
            if (p.left != null){
                queue.addLast(p.left);
            }
            if (p.right != null){
                queue.addLast(p.right);
            }
        }
        return res;
    }
    public boolean VerifySquenceOfBST(int [] sequence) {
        return isPostOrder(sequence,0,sequence.length-1);
    }
    public RandomListNode Clone(RandomListNode pHead) {
        Map<RandomListNode,RandomListNode> already = new HashMap<>();
        RandomListNode pH = pHead;
        RandomListNode res = new RandomListNode(pH.label);
        RandomListNode pR = res;
        while (pH != null){
            if (pH.next != null){
                if (already.containsKey(pH.next)){
                    pR.next = already.get(pH.next);
                }else {
                    pR.next = new RandomListNode(pH.next.label);
                    already.put(pH.next,pR.next);
                }
            }
            if (pH.random != null){
                if (already.containsKey(pH.random)){
                    pR.random = already.get(pH.random);
                }else {
                    pR.random = new RandomListNode(pH.random.label);
                    already.put(pH.random,pR.random);
                }
            }
            pH = pH.next;
            pR = pR.next;
        }
        return res;
    }
    public TreeNode Convert(TreeNode pRootOfTree) {
        TreeNode p = pRootOfTree;
        TreeNode last = null,head = null;
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || p != null){
            while (p != null){
                stack.push(p);
                p = p.left;
            }
            p = stack.pop();
            if (head == null){
                head = p;
            }
            p.left = last;
            if (last != null){
                last.right = p;
            }
            last = p;
            p = p.right;
        }
        return head;
    }
    public ArrayList<String> Permutation(String str) {
        char[] chars = str.toCharArray();
        Arrays.sort(chars);
        str = new String(chars);
        ArrayList<String> res = new ArrayList<>();
        Set<String> set = new TreeSet<>();
        if (str.length() == 0)
            return res;
        res.add(str.charAt(0)+"");
        for (int i = 1;i < str.length();i++){
            char cur = str.charAt(i);
            int size = res.size();
            for (int j = 0;j < size;j++){
                StringBuilder builder = new StringBuilder(res.get(j));
                res.remove(res.get(j));
                for (int k = builder.length();k > 0;k--){
                    if (cur == builder.charAt(k-1))
                        continue;
                    builder.insert(k,cur);
                    res.add(builder.toString());
                    builder.deleteCharAt(k);
                }
                res.add(cur+builder.toString());
            }
        }
        return res;
    }
    public int MoreThanHalfNum_Solution(int [] array) {
        int res = 0;
        int max = 0;
        Map<Integer,Integer> map = new HashMap<>();
        for (int num: array){
            map.put(num,map.getOrDefault(num,0)+1);
            if (map.get(num) > max){
                res = num;
                max = map.get(num);
            }
        }
        res = (map.get(res) > array.length/2)?res:0;
        return res;
    }
    public ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {

        ArrayList<Integer> res = new ArrayList<>();
        if (k > input.length) return res;
        randomSort(input,0,input.length-1,k);
        for (int i = 0;i < k;i++){
            res.add(input[i]);
        }
        return res;
    }
    public int NumberOf1Between1AndN_Solution(int n) {
        if (n <= 9)
            return 1;
        int[] count = new int[n+1];
        int res = 1;
        count[9] = 0;
        for (int i = 10;i <= n;i++){
            int last = i % 10;
            count[i] = count[i-1];
            if (last == 0){
                count[i] = getCountOfX(i,1);
            }else if (last == 1){
                count[i]++;
            }else if (last == 2){
                count[i]--;
            }
            res += count[i];
        }
        return res;
    }
    public String PrintMinNumber(int [] numbers) {
        TreeNode root = null;
        for (int v: numbers){
            root = TreeNode.insertByInitial(root,v);
        }
        StringBuilder builder = new StringBuilder();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode p = root;
        while (!stack.isEmpty() || p != null){
            while (p != null){
                stack.push(p);
                p = p.left;
            }
            p = stack.pop();
            builder.append(p.val);
            p = p.right;
        }
        return builder.toString();
    }
    public int GetUglyNumber_Solution(int index) {
        int[] uglies = new int[index];
        int[] uglyIndex = new int[3];
        int last = uglies[0] = 1;
        for (int i = 1;i <= index;i++){
            last = Math.min(Math.min(uglies[uglyIndex[0]]*2,uglies[uglyIndex[1]]*3),uglies[uglyIndex[2]]*5);
            uglies[i] = last;
            while (uglies[uglyIndex[0]]*2 <= last){
                uglyIndex[0]++;
            }
            while (uglies[uglyIndex[1]]*3 <= last){
                uglyIndex[1]++;
            }
            while (uglies[uglyIndex[2]]*5 <= last){
                uglyIndex[2]++;
            }
        }
        return uglies[index-1];
    }
    public int FirstNotRepeatingChar(String str) {
        boolean[] flags = new boolean[58];
        Map<Character,Integer> map = new LinkedHashMap<>();
        for (int i = 0;i < str.length();i++){
            char c = str.charAt(i);
            if (flags[c-'A']){
                map.remove(c);
                continue;
            }
            map.put(c,i);
            flags[c-'A'] = true;
        }
        return (map.isEmpty())?-1:map.get(map.keySet().iterator().next());
    }
    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        Map<ListNode,Integer> map = new HashMap<>();
        ListNode p = pHead1;
        while (p != null){
            map.putIfAbsent(p,1);
            p = p.next;
        }
        p = pHead2;
        while (p != null){
            int res = map.getOrDefault(p,0);
            if (res == 1){
                return p;
            }
            p = p.next;
        }
        return null;
    }
    public int GetNumberOfK(int [] array , int k) {
        int min1 = 0,max1 = array.length;
        int min2 = 0,max2 = array.length;
        float less = k - 0.01f,more = k + 0.01f;
        while (min1 < max1){
            int mid = (min1+max1)/2;
            if (array[mid] < less){
                min1 = mid+1;
            }else {
                max1 = mid;
            }
        }
        while (min2 < max2){
            int mid = (min2+max2)/2;
            if (array[mid] < more){
                min2 = mid+1;
            }else {
                max2 = mid;
            }
        }
        return min2-min1;
    }
    public int TreeDepth(TreeNode root) {
        if (root == null){
            return 0;
        }else {
            return Math.max(TreeDepth(root.left),TreeDepth(root.right))+1;
        }
    }
    public boolean IsBalanced_Solution(TreeNode root) {
        if (root == null){
            return true;
        }else {
            return IsBalanced_Solution(root.left) && IsBalanced_Solution(root.right) && Math.abs(TreeDepth(root.left)-TreeDepth(root.right)) <= 1;
        }
    }
    public void FindNumsAppearOnce(int [] array,int num1[] , int num2[]) {
        int[] tmp = new int[array.length];
        int res = 0;
        for (int v:array){
            res ^= v;
        }
        int n = Integer.toBinaryString(res).length()-1;
        int begin = 0,last = array.length-1;
        for (int v:array){
            String s = Integer.toBinaryString(v >> n);
            if (s.charAt(s.length()-1) == '1'){
                tmp[begin++] = v;
            }else {
                tmp[last--] = v;
            }
        }
        num1[0] = 0;
        for (int i = 0;i < begin;i++){
            num1[0] ^= tmp[i];
        }
        num2[0] = 0;
        for (int i = begin;i < array.length;i++){
            num2[0] ^= tmp[i];
        }
    }
    public ArrayList<ArrayList<Integer> > FindContinuousSequence(int sum) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        int max = (int)(Math.sqrt(2*sum+0.25)-0.5);
        for (int i = max;i >= 2;i++){
            int begin = (2*sum/i-i+1);
            if (2*sum % i != 0 || begin % 2 != 0)
                continue;
            begin /= 2;
            ArrayList item = new ArrayList();
            for (int j = 1;j <= i;j++){
                item.add(begin++);
            }
            res.add(item);
        }
        return res;
    }
    public ArrayList<Integer> FindNumbersWithSum(int [] array,int sum) {
        ArrayList<Integer> res = new ArrayList<>();
        if (array.length == 0 || sum-array[0] > array[array.length-1])
            return res;
        int i = 0,j = array.length-1;
        while (i < j){
            while (j > i && array[--j] > sum-array[i]);
            if (array[j] == sum-array[i]){
                res.add(array[i]);
                res.add(array[j]);
                break;
            }
        }
        return res;
    }
    public String LeftRotateString(String str,int n) {
        if (str.length() == 0 || n <= 0 || n % str.length() == 0) return str;
        n %= str.length();
        str += str.substring(0,n);
        return str.substring(n);
    }
    public String ReverseSentence(String str) {
        if (str == null || str.length() == 0)
            return str;
        String[] list = str.split(" ");
        if (list.length == 0){
            return str;
        }
        StringBuilder builder = new StringBuilder();
        for (int i = list.length-1;i >= 0;i--){
            builder.append(list[i]);
            builder.append(" ");
        }
        builder.deleteCharAt(builder.length()-1);
        return builder.toString();
    }
    public boolean isContinuous(int [] numbers) {
        int count = 0,nums = 0,sub = 0;
        Arrays.sort(numbers);
        int index = -1;
        while (numbers[++index] != 0);
        count = index;
        nums = 5-count;
        for (int i = index+1;i < 5;i++){
            sub += numbers[i]-numbers[i-1];
        }
        sub -= (nums-1);
        if (sub <= 0) return false;
        return sub <= count;

    }
    public int LastRemaining_Solution(int n, int m) {
        LinkedList<Integer> queue = new LinkedList<>();
        for (int i = 0;i < n;i++){
            queue.addLast(i);
        }
        while (queue.size() != 1){
            for (int i = m;i > 0;i--){
                queue.addLast(queue.pollFirst());
            }
            queue.removeLast();
        }
        return queue.peekFirst();
    }
    public int Sum_Solution(int n) {
        return (1+n)*n/2;
    }
    public int Add(int num1,int num2) {
        int a = num1,b = num2;
        while (b != 0){
            int _a = a ^ b,_b = (a&b) << 1;
            a = _a;
            b = _b;
        }
        return a;
    }
    public int StrToInt(String str) {
        if (str.length() == 0)
            return 0;
        Stack<Integer> stack = new Stack<Integer>();
        int index = 0;
        while (index < str.length() && str.charAt(index++) == 0);
        if (index == str.length())
            return 0;
        str = str.substring(index-1);
        boolean navigate = false;
        for (int i = 0;i < str.length();i++){
            char ch = str.charAt(i);
            if (ch == '-' || ch == '+'){
                navigate = ch == '-';
            }
            if (!Character.isDigit(ch))
                return 0;
            stack.push(ch-'0');
        }
        int q = 1,res = 0;
        while (!stack.isEmpty()){
            res += q * stack.pop();
            q *= 10;
        }
        return (navigate)?-res:res;
    }
    public boolean duplicate(int numbers[],int length,int [] duplication) {
        if (length == 0)
            return false;
        boolean[] flags = new boolean[length];
        for (int i = 0;i < length;i++){
            if (flags[numbers[i]]){
                duplication[0] = numbers[i];
                return true;
            }
            flags[numbers[i]] = true;
        }
        return false;
    }
    public int[] multiply(int[] A) {
        int multi = 1,index = 0;
        int[] B = new int[A.length];
        int[] C = new int[A.length];
        for (int i = 0;i < A.length;i++){
            B[i] = multi;
            multi *= A[i];
        }
        multi = 1;
        for (int i = A.length-1;i >= 0;i--){
            C[i] = multi;
            multi *= A[i];
        }
        for (int i = 0;i < A.length;i++){
            B[i] *= C[i];
        }
        return B;
    }
    //腾讯
    public long SongList2(int k,int a,int x,int b,int y){
        int mod = 1000000007;
        long[] dp = new long[k+1];
        dp[0] = 1;
        for (int i = 0;i < x;i++){
            for (int j = k;j >= a;j--){
                dp[j] = (dp[j]%mod + dp[j-a]%mod)%mod;
            }
        }
        for (int i = 0;i < y;i++){
            for (int j = k;j >= b;j--){
                dp[j] = (dp[j]%mod + dp[j-b]%mod)%mod;
            }
        }
        return dp[k]%mod;
    }
    public int Chocolate(int n,int m){
        int min = 1,max = m,mid=1;
        int total;
        while (min < max){
            mid = (int) Math.ceil((min+max)*0.5);
            total = TotalChocolate(mid,n);
            if (total > m){
                max = mid-1;
            }else if (total < m){
                min = mid;
            }else
                return mid;
        }
        return min;
    }
    public int MinBrushstroke(int n,int m,char[][] board){
        int[][] dir = {{-1,1},{1,1}};
        int res = 0;
        for (int i = 0,j = 0;i <m && j < n;){
            res += _s(i,j,m,n,board,dir,0,'B');
            j = (i == m-1)?j+1:0;
            i = (i == m-1)?m-1:i+1;
        }
        for (int i = m-1,j = 0;i < m && j < n;){
            res += _s(i,j,m,n,board,dir,1,'Y');
            j = (i == 0)?j+1:0;
            i = (i == 0)?0:i-1;
        }
        return res;
    }
    int _s(int i,int j,int m,int n,char[][] board,int[][] dir,int f,char c){
        int x = i,y = j,res = 0;
        boolean flag = false;
        while (!outOfBound(x,y,m,n)){
            if (board[y][x] != c && board[y][x] != 'G'){
                flag = false;
            }else {
                if (!flag) res++;
                flag = true;
            }
            x += dir[f][0];
            y += dir[f][1];
        }
        return res;
    }
    public int gameTaskCheck(int task1,int task2,int[] tasks){
        int n = (task1-1)/32,m = 31-((task1-1)%32);
        long offset = (long)Math.pow(2,m);
        tasks[n] |= offset;
        n = (task2-1)/32;
        m = 31-(task2-1)%32;
        offset = (long) Math.pow(2,m);
        int tmp = tasks[n];
        tmp &= offset;
        if (tmp == 0)
            return -1;
        return 1;
    }
    public int getOddSum(int n){
        int sum = 0;
        for (int i = 1;i <= n/2;i++){
            if (isOdd(i) && isOdd(n-i)) sum++;
        }
        return sum;
    }
    boolean isOdd(int n){
        for (int i = 2;i < n;i++){
            if (n % i == 0)
                return false;
        }
        return true;
    }
    public String GeoHash(int n){
        StringBuilder builder = new StringBuilder();
        int max = 90,min = -90;
        for (int i = 1;i <= 6;i++){
            int mid = (max + min)/2;
            if (n < mid){
                builder.append('0');
                max = mid;
            }else {
                builder.append('1');
                min = mid;
            }
        }
        return builder.toString();
    }

//    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root,int target) {
//        Stack<TreeNode> Nodes = new Stack<>();
//        LinkedList<Integer> nums = new LinkedList<>();
//        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
//
//        Nodes.push(root);
//        TreeNode p;
//        int total = 0;
//        ArrayList<Integer> item = new ArrayList<>();
//        while (!Nodes.isEmpty()){
//            p = Nodes.pop();
//            if (p.val < target-total){
//                nums.addLast(p.val);
//                p = p.left;
//            }else if (p.val >)
//        }
//
//    }


//    public int preimageSizeFZF(int K) {
//        int[] dp = new int[+1];
//        dp[1] = 0;
//        return 0;
//    }
//    public List<List<Integer>> palindromePairs(String[] words) {
//        List<List<Integer>> res = new ArrayList<>();
//        StringBuilder builder;
//        StringBuffer buffer;
//        for (int i = 0;i < words.length;i++){
//            for (int j = 0;j < words.length;j++){
//                if (i == j) continue;
//                builder = new StringBuilder(words[i]).append(words[j]);
//                buffer = new StringBuffer(words[i]).append(words[j]);
//                if (builder.reverse().toString().equals(buffer.toString())){
//                    List<Integer> item = new ArrayList<>();
//                    item.add(i);
//                    item.add(j);
//                    res.add(item);
//                }
//            }
//        }
//        return res;
//    }

    int TotalChocolate(int k,int n){
        int tmp = k,res = 0;
        for (int i = 1;i <= n;i++){
            res += tmp;
            tmp = (int) Math.ceil(tmp*0.5);
        }
        return res;
    }
    double C_mn(int m,int n){
        return (factorial(n)/(factorial(m)*factorial(n-m)))%1000000007;
    }
    double factorial(int n){
        if (n==0)
            return 1;
        double res = 1;
        for (int i = 1;i <= n;i++){
            res *= i;
        }
        return res;
    }
    int less(int N){
        switch (N){
            case 0:
                return 0;
            case 1:
                return 1;
            case 2:
                return 2;
            case 3:
                return 6;
        }
        return 0;
    }
    void swap(int[] list,int i,int j){
        if (i == j)
            return;
        list[i] += list[j];
        list[j] = list[i] - list[j];
        list[i] -= list[j];
    }
    boolean isSubTree(TreeNode rootA, TreeNode rootB){
        if (rootB == null) return true;
        if (rootA == null) return false;
        if (rootA.val != rootB.val){
            return isSubTree(rootA.left,rootB)||isSubTree(rootA.right,rootB);
        }else  return false;
    }
    boolean isPostOrder(int[] sequence,int i,int j){
        if (j-i >= 3){
            int cur = sequence[j];
            int mid = j;
            while (mid > i && sequence[--mid] > cur);
            if (mid <= i) return true;
            while (mid > i){
                if (sequence[--mid] > cur)
                    return false;
            }
            return isPostOrder(sequence,i,mid) && isPostOrder(sequence,mid+1,j-1);
        }else {
            return true;
        }
    }
    void randomSort(int[] list,int left,int right,int k){
        if (left < right){
            int mid = randomPartition(list,left,right);
            randomSort(list,left,mid-1,k);
            if (mid+1 < k)
                randomSort(list,mid+1,k-1,k);
        }
    }
    int randomPartition(int[] list,int left,int right){
       int i = left,j = right+1;
       int x = list[left];
       while (true){
           while (list[++i] < x && i < right);
           while (list[--j] > x);
           if (i >= j) break;
           swap(list,i,j);
       }
       swap(list,left,j);
       return j;
    }
    //
    int[][] dirs = {{0,1},{1,0},{0,-1},{-1,0}};
    boolean[][] isVisited;
    void dfs(int[][] matrix,int i,int j,int dir,List<Integer> res){
        if (outOfBound(i,j,matrix.length,matrix[0].length) || isVisited[i][j])
            return;
        isVisited[i][j] = true;
        res.add(matrix[i][j]);
        int x = dirs[dir][0] + i;
        int y = dirs[dir][1] + j;
        if (outOfBound(x,y,matrix.length,matrix[0].length) || isVisited[x][y]){
            dir = (dir+1 == 4)?0:dir+1;
            x = dirs[dir][0] + i;
            y = dirs[dir][1] + j;
        }
        dfs(matrix,x,y,dir,res);
    }
    boolean outOfBound(int x,int y,int lenX,int lenY){
        boolean flag = (x >= lenX || y >= lenY || x < 0 || y < 0);
        return flag;
    }
    //
    int getCountOfNum(int num){
        return Integer.toString(num,10).length();
    }
    int[] getNumDigits(int num){
        String s = Integer.toString(num,10);
        int n = s.length();
        int[] res = new int[n];
        for (int i = 0;i < n;i++){
            res[i] = s.charAt(i)-'0';
        }
        return res;
    }
    int getCountOfX(int num,int x){
        StringBuilder builder = new StringBuilder(num+"");
        int res = 0;
        int index;
        while ((index=builder.indexOf(x+"")) != -1){
            res++;
            builder.deleteCharAt(index);
        }
        return res;
    }

}
class Debug{
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] a = {12,24,8,32};
        int[] b = {13,25,32,11};
        String[] strings = {"Hello","Alaska","Dad","Peace"};
        for (String s: solution.findWords(strings)){
            System.out.println(s);
        }
        System.out.println("finish");
    }
}
class NumArray {

    int[] list;
    int[] sums;
    int length;
    public NumArray(int[] nums) {
        length = nums.length;
        if (length > 0){
            list = nums;
            sums = new int[nums.length];
            sums[0] = nums[0];
            for (int i = 1;i < nums.length;i++){
                sums[i] = sums[i-1] + nums[i];
            }
        }
    }

    public void update(int i, int val) {
        if (i < length){
            int sub = val - list[i];
            list[i] = val;
            for (int index = i;index < list.length;index++){
                sums[index] += sub;
            }
        }
    }

    public int sumRange(int i, int j) {
        if (i < length && j < length)
            return  sums[j] - sums[i] + list[i];
        else return 0;
    }
}





