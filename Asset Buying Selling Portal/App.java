import java.io.*;
import java.sql.*;
import java.util.*;

class App {
    static Statement st;
    static Scanner sc ;
    static Connection con;

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        App ap = new App();
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/asset_buying_selling_portal", "root", "");
        if(con!=null)
        {
            System.out.println("Sucessfull.");
        }
        else
        {
            System.out.println("Failed.");
        }

         st = con.createStatement();

        System.out.println("Are you Buyer or Seller.");
        System.out.println("Enter your choice : ");
        System.out.println("1.Buyer.");
        System.out.println("2.Seller.");
        System.out.println("3.System Operator.");
        int a;
         a = sc.nextInt();
         sc.nextLine();
        switch (a) {
        case 1: 
                ap.display();
                ap.Buy();
                break;
            
        case 2:  
                int b;
                do{
                System.out.println("Enter your choice : ");
                System.out.println("1.Login");
                System.out.println("2.New User");
                System.out.println("3.Exit.");
                b = sc.nextInt();
                sc.nextLine();
                switch (b) {
                case 1:  
                    System.out.println("Enter your Id : ");
                    int id=sc.nextInt();
                    sc.nextLine();
                    System.out.println("Enter your Password : ");
                    String pass = sc.nextLine();
                    String sql = "SELECT COUNT(*) FROM seller WHERE s_id = "+id+" and s_pass='"+pass+"'";

                    ResultSet resultSet = st.executeQuery(sql);
                    if (resultSet.next()) 
                    {
                        int count = resultSet.getInt(1);
                        if (count > 0) 
                        {
                        System.out.println("The data is available in the table.");
                        System.out.println("what do you want to do ?");
                        System.out.println("1.view assest details");
                        System.out.println("2.Update price in your asset.");
                        System.out.println("3.Sell Asset.");
                        System.out.println("4. Exit");
                        int c;
                            do{
                            c = sc.nextInt();
                            switch (c) {
                                case 1:
                                    String sql_sel = "SELECT * FROM assets WHERE s_id = "+id;
                                    ResultSet rs_view = st.executeQuery(sql_sel);
                                    while(rs_view.next()){
                                        int id_ass = rs_view.getInt("a_id");
                                    int id_sell = rs_view.getInt("s_id");
                                    String type = rs_view.getString("a_type");
                                    String location =  rs_view.getString("a_location");
                                    String owner = rs_view.getString("a_owner");
                                    double price = rs_view.getDouble("a_price");
                                    String detail = rs_view.getString("a_details");
                                   
                                 
                                    System.out.println("***************************");
                                    System.out.println();
                                    System.out.println("Asset id : " + id_ass);
                                    System.out.println("Seller id : " + id_sell);
                                    System.out.println("Asset Type : "+type);
                                    System.out.println("Asset Location : "+location);
                                    System.out.println("Asset Owner : "+owner);
                                    System.out.println("Asset Price : "+price);
                                    System.out.println("Asset Detail : "+detail);
                                    System.out.println();
                                    System.out.println("***************************");
                                    }
                                    break;
                                case 2:
                                    System.out.println("Enter your asset Id : ");
                                    int aid=sc.nextInt();
                                    System.out.println("Enter new price: ");
                                    double pr1 = sc.nextDouble();
                                    double pr=1.1*pr1;

                                    String upd_price = "update assets set a_price=? where a_id=?";
                                    PreparedStatement pst = con.prepareStatement(upd_price);
                                    pst.setDouble(1, pr);
                                    pst.setInt(2, aid);

                                    int p = pst.executeUpdate();

                                    if(p>0){
                                        System.out.println("Price Updated Successfully.");
                                    }
                                    else{
                                        System.out.println("Enter Valid Argument.");
                                    }
                                    break;
                                case 3:
                                    ap.insrert_asset();
                                    break;
                                case 4:
                                break;
                                    
                                default:System.out.println("Enter write choice. ");
                                    break;
                                }
                                System.out.println("1.view assest details");
                                System.out.println("2.Update price in your asset.");
                                System.out.println("3.Sell Asset.");
                            }while(c!=4);
                    } else {
                        System.out.println("The data is not available in the table.");
                    }
                    }
                    break;
                case 2:
                    System.out.println("Enter your Name : ");
                    String name1=sc.nextLine();
                    System.out.println("Create your Password : ");
                    String pass1 = sc.nextLine();
                    String in = "insert into seller(s_name,s_pass) values('"+name1+"','"+pass1+"')";
                    int r = st.executeUpdate(in);
                    if(r>0)
                    {
                        System.out.println("Inserted Your Asset Successfully...");
                        String out_sid = "select s_id from seller where s_name = '"+name1+"' and s_pass = '"+pass1+"'";
                        ResultSet rs_sid = st.executeQuery(out_sid);
                        while(rs_sid.next()){
                            System.out.println("Your seller id is: "+rs_sid.getInt("s_id"));
                            System.out.println();
                        }
                        ap.insrert_asset();
                        
                    }
                    else
                    {
                        System.out.println("Insertion fail...");
                    }
                    break;    
                case 3:
                    break;
                default:System.out.println("Enter right choice ");
                    break;
                }
                }while(b!=3);
            
                System.out.println("Enter your choice : ");
                System.out.println("1.Buyer.");
                System.out.println("2.Seller.");
                System.out.println("3.System Operator.");
                System.out.println("4.Exit.");
                break;   
                
        case 3:
        System.out.println("Enter Operator Password : ");
        String pass1="Asset@1234";
                String pass2=sc.nextLine();

            if(pass2.equals(pass1))  
            {                
            while(true){
                System.out.println("Enter Your choice : ");
                System.out.println("1.Get Asset Table File.");
                System.out.println("2.Get Buyer Table File.");
                System.out.println("3.Get Final List Table File.");
                System.out.println("4.Exit.");
                int d =sc.nextInt();
                                        switch (d) 
                                        {
                                            case 1:
                                                File f = new File("D:\\Divyrajsinh_Project\\Project1\\Assets.txt");
                                                f.createNewFile();
                                                BufferedReader br = new BufferedReader(new FileReader(f));
                                                BufferedWriter bw = new BufferedWriter(new FileWriter(f));

                                                String sql1 = "SELECT * FROM assets";
                                                ResultSet resultSet1 = st.executeQuery(sql1);
                                                while (resultSet1.next()) 
                                                {
                                                    int id2 = resultSet1.getInt("a_id");
                                                    int id1 = resultSet1.getInt("s_id");
                                                    String type = resultSet1.getString("a_type");
                                                    String location =  resultSet1.getString("a_location");
                                                    String owner = resultSet1.getString("a_owner");
                                                    double price = resultSet1.getDouble("a_price");
                                                    String detail = resultSet1.getString("a_details");
                                                    bw.write("Asset id : "+id2);
                                                    bw.newLine();
                                                    bw.write("Seller id : "+id1);
                                                    bw.newLine();
                                                    bw.write("Asset Type : "+type);
                                                    bw.newLine();
                                                    bw.write("Asset Location : "+location);
                                                    bw.newLine();
                                                    bw.write("Asset Owner : "+owner);
                                                    bw.newLine();
                                                    bw.write("Asset price : "+price);
                                                    bw.newLine();
                                                    bw.write("Asset Detail : "+detail);
                                                    bw.newLine();
                                                    bw.flush();
                                                }
                                                break;
                                            case 2:
                                                File f1 = new File("D:\\Divyrajsinh_Project\\Project1\\Buyer.txt");
                                                f1.createNewFile();
                                                BufferedReader br1 = new BufferedReader(new FileReader(f1));
                                                BufferedWriter bw1 = new BufferedWriter(new FileWriter(f1));

                                                String sql2 = "SELECT * FROM buyer";
                                                ResultSet resultSet2 = st.executeQuery(sql2);
                                                while (resultSet2.next()) 
                                                {
                                                    int id2 = resultSet2.getInt("b_id");
                                                    int id1 = resultSet2.getInt("a_id");
                                                    String name = resultSet2.getString("b_name");
                                                    double price = resultSet2.getDouble("a_price");
                                                    bw1.newLine();
                                                    bw1.write("Buyer id : "+id2);
                                                    bw1.newLine();
                                                    bw1.write("Asset id : "+id1);
                                                    bw1.newLine();
                                                    bw1.write("Byuer Name : "+name);
                                                    bw1.newLine();
                                                    bw1.write("Asset price : "+price);
                                                    bw1.newLine();
                                                    bw1.flush();
                                                }
                                                break;
                                            
                                            case 3:
                                                File f3 = new File("D:\\Divyrajsinh_Project\\Project1\\Final_List.txt");
                                                f3.createNewFile();
                                                BufferedReader br3 = new BufferedReader(new FileReader(f3));
                                                BufferedWriter bw3 = new BufferedWriter(new FileWriter(f3));

                                                String sql4 = "SELECT * FROM final_list";
                                                ResultSet resultSet4 = st.executeQuery(sql4);
                                                while (resultSet4.next()) 
                                                {
                                                    int id1 = resultSet4.getInt("a_id");
                                                    int id2 = resultSet4.getInt("s_id");
                                                    String old_name = resultSet4.getString("old_owner");
                                                    int id3 = resultSet4.getInt("b_id");
                                                    String new_name = resultSet4.getString("new_owner");
                                                    double price = resultSet4.getDouble("a_price");
                                                    double pro = resultSet4.getDouble("profit");
                                                    bw3.newLine();
                                                    bw3.write("Asset id : "+id1);
                                                    bw3.newLine();
                                                    bw3.write("Seller id : "+id2);
                                                    bw3.newLine();
                                                    bw3.write("Old Owner name : "+old_name);
                                                    bw3.newLine();
                                                    bw3.write("Buyer id : "+id3);
                                                    bw3.newLine();
                                                    bw3.write("New Owner name : "+new_name);
                                                    bw3.newLine();
                                                    bw3.write("Asset Price : "+price);
                                                    bw3.newLine();
                                                    bw3.write("Profit : "+pro);
                                                    bw3.newLine();
                                                    bw3.flush();
                                                }
                                                break;
                                                case 4: System.exit(4);
                                                break;
                                            default:System.out.println();
                                            System.out.println("enter valid choice.");
                                            System.out.println();
                                                break;
                                        }
                                    }
                                }
                                else
                                {
                                    System.out.println("You have entered wrong pass.");
                                }
                                break;
                
            default:System.out.println("You have entered Invalid choice.");
                break;
        
        }

    }
    
    void insrert_asset()throws Exception
    {
        sc=new Scanner(System.in);
        st = con.createStatement();
        System.out.println("Enter your s_id : ");
        int id =sc.nextInt();
        sc.nextLine();
        System.out.println("Enter asset type : ");
        String type = sc.nextLine();
        System.out.println("Enter asset Location :");
        String Location = sc.nextLine();
        System.out.println("Enter Owner name : ");
        String oName = sc.nextLine();
        System.out.println("Enter Price");
        Long price = sc.nextLong();
        double price1 = price*1.1;
        sc.nextLine();
        System.out.println("Enter Detail about asset");
        String detail = sc.nextLine();

         String in = "insert into assets(s_id,a_type,a_Location,a_Owner,a_price,a_details) values('"+id+"','"+type+"','"+Location+"','"+oName+"','"+price1+"','"+detail+"')";
                int r = st.executeUpdate(in);
                if(r>0)
                {
                    System.out.println("Asset Added....");
                }
                else
                {
                    System.out.println("Not Added Asset.....");
                }

    }

    
    void Buy()throws Exception   
    {
        sc=new Scanner(System.in);
        System.out.println("Enter your name : ");
        String name=sc.nextLine(); 
        System.out.println("Enter Asset id no. to buy :");
        int id = sc.nextInt();

        String sql1 = "SELECT * FROM assets where a_id="+id+" ";

        ResultSet resultSet = st.executeQuery(sql1);
        int id1=0;
        int id2=0;
        double price=0;
        double pro=0;
        String owner="";
        
        while (resultSet.next()) {
            
             id2 = resultSet.getInt("a_id");
             id1 = resultSet.getInt("s_id");
            String type = resultSet.getString("a_type");
            String location =  resultSet.getString("a_location");
             owner = resultSet.getString("a_owner");
            price = resultSet.getDouble("a_price");
            String detail = resultSet.getString("a_details");
            pro=0.1*price;
            
        }
            String buyer = "insert into buyer(a_id,b_name,a_price)values("+id2+",'"+name+"',"+price+")";
            int r2=st.executeUpdate(buyer);
            String ab="Select  b_id from buyer where a_id="+id+"";
               ResultSet resultSet1 =st.executeQuery(ab);
               int bid=0;
               while (resultSet1.next())
                {
                    bid = resultSet1.getInt("b_id");
                }

               
            if(id1!=0&&id2!=0&&price!=0&&pro!=0)
            {
                String i_final="insert into final_list(a_id,s_id,old_owner,b_id,new_owner,a_Price,profit)values("+id+","+id1+",'"+owner+"',"+bid+",'"+name+"',"+price+","+pro+")";
                int r = st.executeUpdate(i_final);
                if(r>0)
                {
                    System.out.println(" Successfully Added Asset...");
                }
                else
                {
                    System.out.println("Not Added Asset...");
                }

                
            }
           

             String sql = "delete from assets  where a_id ="+id;
        int r1  = st.executeUpdate(sql);
        if(r1>0)
                {
                    System.out.println("You Successfully Taken this  Asset...");
                }
                else
                {
                    System.out.println("Asset id not avilable.");
                }
    }
   
    void display()throws Exception
    {
        st = con.createStatement();

        String sql = "SELECT * FROM assets";
        ResultSet resultSet = st.executeQuery(sql);

        while (resultSet.next()) 
        {
            int id = resultSet.getInt("a_id");
            int id1 = resultSet.getInt("s_id");
            String type = resultSet.getString("a_type");
            String location =  resultSet.getString("a_location");
            String owner = resultSet.getString("a_owner");
            double price = resultSet.getDouble("a_price");
            String detail = resultSet.getString("a_details");
           
            System.out.println("Asset id : " + id);
            System.out.println("Seller id : " + id1);
            System.out.println("Asset Type : "+type);
            System.out.println("Asset Location : "+location);
            System.out.println("Asset Owner : "+owner);
            System.out.println("Asset Price : "+price);
            System.out.println("Asset Detail : "+detail);
            System.out.println();
            System.out.println("*******************************");
            System.out.println();
        }
    }
}