INSERT INTO product (company_name, page_text, bold_text, text, image_path) VALUES
('Rexnord', 'Rexnord text', 'Промышленные цепи. Cпецификации, соответствующие высшим отраслевым стандартам.', 'Поставка со склада в Германии.', 'uploads/products/rexnord.JPEG'),
('Technofiliere', 'Technofiliere text' ,'Экструзионные матрицы. Оборудование тяжелой глиняной и керамической промышленности.', 'Поставка со склада в Италии.', 'uploads/products/technofiliere.JPEG'),
('Laterimpianti', 'Laterimpianti text', 'Производство многоковшовых экскаваторов, запасных частей и комплектующих.', 'Поставка со склада в Италии.', 'uploads/products/laterimpianti.JPEG'),
('Ironoxide', 'Ironoxide text', 'Оксид железа Fe2O3. Cинтетический оксид железа SKM-ARP 1 используется как пигмента для окраски глины при производстве кирпича.', 'Производство компании EG, Корея, Республика', 'uploads/products/ironoxide.JPEG');

INSERT INTO product_photos (product_id, photo_url) VALUES
(1, '/uploads/photos/blueprint1.png'),
(1, '/uploads/photos/blueprint2.png');

INSERT INTO product_documents (product_id, document_url) VALUES
(1, '/uploads/documents/горелка ASG1T.pdf');