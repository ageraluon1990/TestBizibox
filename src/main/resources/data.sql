INSERT INTO contact_list (user_name, phone) VALUES
      ('moshe', '0513153847'),
      ('david', '0518617755'),
      ('dana', '0596580283'),
      ('michal', '0594372654'),
      ('erez', '0511142653'),
      ('guy', '0598558770');

INSERT INTO black_list (phone) VALUES
        ('0560265187'),
        ('0563803925'),
        ('0510888303'),
        ('0567457626'),
        ('0595760680');

INSERT INTO call_data(time_now, call_type, duration, phone_number, save_contact) VALUES
           ('2010-10-10 17:09:59', "Incoming", 640, "0513153847", false),
           ('2008-10-21 09:10:30', "Outgoing", 179, "0518617755", false),
           ('2004-10-21 11:30:42', "Incoming", 179, "0596580283", false),
           ('2006-10-21 10:10:10', "Outgoing", 720, "0564210275", false),
           ('2008-10-21 21:15:21', "Incoming", 15, "0560265187", false);