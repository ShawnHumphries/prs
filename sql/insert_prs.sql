-- insert some rows into the users table
INSERT INTO users VALUES
(1, 'Bono', 'Bono_Vox', 'Paul', 'Hewson', '5022233125', 'bono@u2.com', 1),
(2, 'Edge', 'guitar', 'Dave', 'Evans', '4247314483', 'edge@u2.com', 0),
(3, 'Adam', 'bass', 'Adam', 'Clayton', '2123858815', 'adam@u2.com', 0);

-- insert some rows into the Vendor table
INSERT into vendors VALUES
(1, 'BB-1001   ', 'Best Buy', '100 Best Buy Street', 'Louisville', 'KY', '40207', '502-111-9099', 'geeksquad@bestbuy.com', 0),
(2, 'AP-1001   ', 'Apple Inc', '1 Infinite Loop', 'Cupertino', 'CA', '95014', '800-123-4567', 'genius@apple.com', 0),
(3, 'AM-1001   ', 'Amazon', '410 Terry Ave. North', 'Seattle', 'WA', '98109','206-266-1000', 'amazon@amazon.com', 1),
(4, 'ST-1001   ', 'Staples', '9550 Mason Montgomery Rd', 'Mason', 'OH', '45040', '513-754-0235', 'support@orders.staples.com', 1),
(5, 'MC-1001   ', 'Micro Center', '11755 Mosteller Rd', 'Sharonville', 'OH', '45241', '513-782-8500', 'support@microcenter.com', 1);

-- insert some rows into the Product table
INSERT INTO products (ID, Name, PartNumber, Price, Unit, VendorID, PhotoPath) VALUES (1, 'iPad Mini 2', 'ME280LL', 296.99, NULL, 1, NULL);
INSERT INTO products (ID, Name, PartNumber, Price, Unit, VendorID, PhotoPath) VALUES (2, 'iPad Mini 2', 'ME280LL', 299.99, NULL, 2, NULL);
INSERT INTO products (ID, Name, PartNumber, Price, Unit, VendorID, PhotoPath) VALUES (3, 'Hammermill Paper, Premium Multi-Purpose Paper Poly Wrap', '105810', 8.99, '500 Sheets', 3, NULL);
INSERT INTO products (ID, Name, PartNumber, Price, Unit, VendorID, PhotoPath) VALUES (4, 'HammerMill® Copy Plus Copy Paper, 8 1/2\" x 11\", Case', '122374', 29.99, '1 Case, 10 Reams, 500 Sheets per ream', 4, NULL);
INSERT INTO products (ID, Name, PartNumber, Price, Unit, VendorID, PhotoPath) VALUES (5, 'Logitech M325 Wireless Optical Mouse, Ambidextrous, Black ', '784551', 14.99, NULL, 4, NULL);
INSERT INTO products (ID, Name, PartNumber, Price, Unit, VendorID, PhotoPath) VALUES (6, 'Staples Mouse Pad, Black', '382955', 2.99, NULL, 4, NULL);
INSERT INTO products (ID, Name, PartNumber, Price, Unit, VendorID, PhotoPath) VALUES (7, 'AOC 24-Inch Class LED Monitor', '2122178', 99.99, NULL, 4, NULL);
INSERT INTO products (ID, Name, PartNumber, Price, Unit, VendorID, PhotoPath) VALUES (8, 'Laptop HP Notebook 15-AY163NR', '2460649', 529.99, NULL, 4, NULL);
INSERT INTO products (ID, Name, PartNumber, Price, Unit, VendorID, PhotoPath) VALUES (9, 'Laptop Dell i3552-3240BLK 15.6\"', '2256788', 239.99, NULL, 4, NULL);
INSERT INTO products (ID, Name, PartNumber, Price, Unit, VendorID, PhotoPath) VALUES (10, 'Laptop Acer Acer™ Aspire One Cloudbook 14\"', 'IM12M9520', 224.99, NULL, 4, NULL);
INSERT INTO products (ID, Name, PartNumber, Price, Unit, VendorID, PhotoPath) VALUES (11, 'Canon imageCLASS Copier (D530)', '940600', 99.99, NULL, 4, NULL);
INSERT INTO products (ID, Name, PartNumber, Price, Unit, VendorID, PhotoPath) VALUES (12, 'Acer Aspire ATC-780A-UR12 Desktop Computer', '228148', 399.99, '', 5, 'http://www.assistiveit.co.uk/filedepository/hardware%20-%20miscellaneous/thumbnails/acer_desktop_800_800_130305125943.jpg');
INSERT INTO products (ID, Name, PartNumber, Price, Unit, VendorID, PhotoPath) VALUES (13, 'Lenovo IdeaCentre All-In-One Desktop', '279364', 349.99, NULL, 5, NULL);
