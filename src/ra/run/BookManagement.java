package ra.run;

import ra.bussiness.Book;
import ra.config.Config;

public class BookManagement {

    static Book[] listBook = new Book[100];
    public static void main(String[] args) {
        while (true) {
            System.out.println("****************JAVA-HACKATHON-05-BASIC-MENU***************");
            System.out.println("1. Nhập số lượng sách thêm mới và nhập thông tin cho từng cuốn sách");
            System.out.println("2. Hiển thị thông tin tất cả sách trong thư viện");
            System.out.println("3. Sắp xếp sách theo lợi nhuận tăng dần");
            System.out.println("4. Xóa sách theo mã sách");
            System.out.println("5. Tìm kiếm tương đối sách theo tên sách hoặc mô tả");
            System.out.println("6. Thay đổi thông tin sách theo mã sách");
            System.out.println("7. Thoát");
            System.out.println("Hãy nhập vào lựa chọn của bạn");
            int choice = Config.scanner().nextInt();
            switch (choice){
                case 1:
                    createBook();
                    break;
                case 2:
                    showAllBook();
                    break;
                case 3:
                    // Sap xep theo loi nhuan
                    showSort();
                    break;
                case 4:
                    // xoa theo id
                    deleteBook();
                    break;
                case 5:
                    // Search
                    searchBook();
                    break;
                case 6:
                    updateBook();
                    break;
                case 7:
                    System.out.println("Thoát chương trình");
                    System.exit(0);
                default:
                    System.err.println("hãy nhập số từ 1 đến 7");
            }
        }
    }

    public static void createBook(){
        System.out.println("Nhập số lượng sách n: ");
        int n = Config.scanner().nextInt();
        for (int i = 0; i < n; i++) {
            listBook[i] = new Book();
            listBook[i].setBookId(i + 1);
            System.out.printf("Nhap sach thu %d\n", i+1);
            System.out.println("Nhap ten sach: ");
            listBook[i].setBookName(Config.scanner().nextLine());
            System.out.println("Nhap tac gia: ");
            listBook[i].setAuthor(Config.scanner().nextLine());
            System.out.println("Nhap mo ta: ");
            listBook[i].setDescriptions(Config.scanner().nextLine());
            System.out.println("Nhap gia nhap: ");
            listBook[i].setImportPrice(Config.scanner().nextDouble());
            System.out.println("Nhap gia xuat: ");
            listBook[i].setExportPrice(Config.scanner().nextDouble());
            listBook[i].setInterest(listBook[i].inputData());
            listBook[i].setBookStatus(true);
        }
    }


    public static void showAllBook() {
        for (Book e:listBook) {
            if(e != null) {
                System.out.println(e);
            }
        }
    }

    public static void showSort(){
        Book[] listBook2 = new Book[100];
        int size = 0;

        for (int i = 0; i < listBook.length; i++) {
            if (listBook[i] != null) {
                listBook2[size] = listBook[i];
                size++;
            }
        }

        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                if (listBook2[j].getInterest() > listBook2[j + 1].getInterest()) {
                    Book temp = listBook2[j];
                    listBook2[j] = listBook2[j + 1];
                    listBook2[j + 1] = temp;
                }
            }
        }
        for (int i = 0; i < size; i++) {
            System.out.println(listBook2[i]);
        }
    }

    public static void deleteBook(){
        System.out.println("Nhập id xoa: ");
        int id = Config.scanner().nextInt();
        if(id < 1 || id > listBook.length){
            System.err.println("Nhap id khong hop le");
        }
        for (int i = 0; i < listBook.length; i++) {
            if (listBook[i] != null && listBook[i].getBookId() == id) {
                listBook[i] = null;
                System.out.println("Xoa thanh cong");
                return;
            } else {
                System.err.println("loi");
            }
        }
    }

    public static void searchBook(){
        System.out.println("Nhap ten sach hoac mo ta : ");
        String str = Config.scanner().nextLine();
        boolean check = false;
        for (Book e : listBook){
            if (e != null && (e.getBookName().trim().equals(str.trim()) || e.getDescriptions().trim().equals(str.trim()))){
                check = true;
                System.out.println(e);
            }
        }
        if(check == false){
            System.err.println("Khong tim thay sach");
        }
    }

    public static void updateBook(){
        System.out.println("Nhập id ma sach cap nhat: ");
        int id = Config.scanner().nextInt();
        if(listBook[id-1] != null){
            listBook[id-1].setBookId(id);
            System.out.println("Nhap ten sach: ");
            listBook[id-1].setBookName(Config.scanner().nextLine());
            System.out.println("Nhap tac gia: ");
            listBook[id-1].setAuthor(Config.scanner().nextLine());
            System.out.println("Nhap mo ta: ");
            listBook[id-1].setDescriptions(Config.scanner().nextLine());
            System.out.println("Nhap gia nhap: ");
            listBook[id-1].setImportPrice(Config.scanner().nextDouble());
            System.out.println("Nhap gia xuat: ");
            listBook[id-1].setExportPrice(Config.scanner().nextDouble());
            listBook[id-1].setInterest(listBook[id-1].inputData());
            listBook[id-1].setBookStatus(true);
            System.out.println("Update thanh cong");
        }
    }
}
