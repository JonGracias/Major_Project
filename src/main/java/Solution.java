import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



class Solution {
    public static void main(String[] args) throws IOException {
        List<List<String>> aList = new ArrayList<>();
        List<String> list=new ArrayList<>(Arrays.asList("https://wp-assets.airbrake.io/wp-content/uploads/2022/03/04221821/Eeron-bug-squash-small.png.com"));
        aList.add(list);
        System.out.println(validateImageSize(aList, "35"));
    }
    /*
     * Complete the 'validateImageSize' function below.
     *
     * The function is expected to return a 2D_STRING_ARRAY.
     * The function accepts following parameters:
     *  1. 2D_STRING_ARRAY imageUrls
     *  2. STRING maxSize
     */

    public static List<List<String>> validateImageSize(List<List<String>> imageUrls, String maxSize) throws IOException {
        List<List<String>> aList = new ArrayList<>();
        List<String> bList = new ArrayList<>();
        HttpURLConnection conn = null;
        for (List<String> imageUrl : imageUrls) {
            try {
                URL url = new URL(imageUrl);
                conn = (HttpURLConnection) url.openConnection();
                String a = imageUrl.toString();

                long size = conn.getContentLengthLong();
                long mSize = Long.parseLong(maxSize);
                if(size > mSize){
                    bList.add(a + "FALSE");
                }else{
                    bList.add(a + "TRUE");

                }
            }catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                if (conn != null){
                    conn.disconnect();
                }
            }
        }
        aList.add(bList);
        return aList;
    }

}

//public class Solution {
//    public static void main(String[] args) throws IOException {
//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
//
//        int imageUrlsRows = Integer.parseInt(bufferedReader.readLine().trim());
//        int imageUrlsColumns = Integer.parseInt(bufferedReader.readLine().trim());
//
//        List<List<String>> imageUrls = new ArrayList<>();
//
//        IntStream.range(0, imageUrlsRows).forEach(i -> {
//            try {
//                imageUrls.add(
//                        Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
//                                .collect(toList())
//                );
//            } catch (IOException ex) {
//                throw new RuntimeException(ex);
//            }
//        });
//
//        String maxSize = bufferedReader.readLine();
//
//        List<List<String>> result = Result.validateImageSize(imageUrls, maxSize);
//
//        result.stream()
//                .map(
//                        r -> r.stream()
//                                .collect(joining(" "))
//                )
//                .map(r -> r + "\n")
//                .collect(toList())
//                .forEach(e -> {
//                    try {
//                        bufferedWriter.write(e);
//                    } catch (IOException ex) {
//                        throw new RuntimeException(ex);
//                    }
//                });
//
//        bufferedReader.close();
//        bufferedWriter.close();
//    }
//}