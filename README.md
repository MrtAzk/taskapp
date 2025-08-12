# 📋 Task Management Application

Modern ve güvenli bir görev yönetim sistemi. Spring Boot ecosystem'ü kullanılarak geliştirilmiş, JWT authentication ve role-based authorization içeren full-stack backend uygulaması.

## 🛠️ Teknoloji Stack

- **Spring Boot 3.5.4** - Ana framework
- **Spring Security** - Güvenlik ve yetkilendirme
- **Spring Data JPA** - ORM ve veritabanı işlemleri
- **PostgreSQL** - İlişkisel veritabanı
- **JWT (JSON Web Token)** - Stateless authentication
- **ModelMapper** - DTO mapping
- **Lombok** - Code generation
- **Maven** - Dependency management

## ⚡ Temel Özellikler

### 🔐 Authentication & Security
- JWT tabanlı kullanıcı kimlik doğrulama
- BCrypt ile şifre hashleme
- Role-based authorization (USER/ADMIN)
- Session'sız (stateless) güvenlik

### 👥 Kullanıcı Yönetimi
- Kullanıcı kayıt ve giriş sistemi
- Profil bilgileri yönetimi
- Kullanıcı listesi ve detay görüntüleme

### 📁 Proje Yönetimi
- Proje oluşturma ve düzenleme
- Proje detayları ve görev listesi
- Sayfalama ile proje görüntüleme

### ✅ Görev Yönetimi
- Görev oluşturma ve atama
- Durum takibi (TODO, IN_PROGRESS, DONE)
- Proje bazlı görev organizasyonu
- Kullanıcıya özel görev ataması

## 🏗️ Proje Mimarisi

```
📦 taskapp
├── 🎮 api/              # REST Controllers
├── 💼 business/         # Business Logic Layer
│   ├── abstracts/       # Service Interfaces
│   └── concretes/       # Service Implementations
├── ⚙️ core/             # Core Components
│   ├── config/          # Configurations
│   ├── exception/       # Exception Handling
│   └── utils/           # Utility Classes
├── 🗄️ dao/              # Data Access Layer
├── 📦 dto/              # Data Transfer Objects
└── 🏗️ entities/         # JPA Entities
```

## 🚀 Kurulum ve Çalıştırma

### Gereksinimler
- Java 17+
- PostgreSQL 12+
- Maven 3.6+

### Adımlar

1. **Repository'yi klonlayın**
```bash
git clone https://github.com/[username]/taskapp.git
cd taskapp
```

2. **PostgreSQL veritabanını oluşturun**
```sql
CREATE DATABASE TaskAppProject;
```

3. **Veritabanı ayarlarını kontrol edin**
`application.properties` dosyasındaki connection bilgilerini güncelleyin.

4. **Uygulamayı başlatın**
```bash
./mvnw spring-boot:run
```

Uygulama `http://localhost:8080` adresinde çalışmaya başlayacaktır.

## 📡 API Kullanımı

### Authentication
```http
POST /login/save
Content-Type: application/json

{
  "name": "mert",
  "email": "mert@example.com", 
  "password": "123456"
}
```

### Proje Oluşturma
```http
POST /v1/projects
Authorization: Bearer {jwt_token}
Content-Type: application/json

{
  "name": "Web Projesi",
  "description": "Şirket web sitesi geliştirme"
}
```

### Görev Oluşturma
```http
POST /v1/tasks
Authorization: Bearer {jwt_token}
Content-Type: application/json

{
  "title": "Frontend Development",
  "description": "React ile arayüz geliştirme",
  "assignedUserId": 1,
  "projectId": 1
}
```

## 🎯 Öne Çıkan Teknik Özellikler

- **Layered Architecture**: Clean separation of concerns
- **DTO Pattern**: Request/Response ayrımı ile güvenlik
- **Global Exception Handling**: Merkezi hata yönetimi
- **Pagination Support**: Büyük veri setleri için optimize edilmiş listeleme
- **Bean Validation**: Giriş verilerinin doğrulanması
- **ModelMapper Integration**: Otomatik DTO dönüşümleri

## 📊 Veritabanı İlişkileri

- **User → Task**: One-to-Many (Bir kullanıcının birden fazla görevi)
- **Project → Task**: One-to-Many (Bir projenin birden fazla görevi)
- **User → Project**: One-to-Many (Bir kullanıcının birden fazla projesi)

## 🔮 Gelecek Planları

- [ ] Frontend (React) entegrasyonu
- [ ] Email notification sistemi
- [ ] File upload desteği
- [ ] Advanced filtering ve search
- [ ] Dashboard ve analytics

## 📧 İletişim

**Geliştirici**: Mert-Azko  
**LinkedIn**: [https://www.linkedin.com/in/mert-azko-]  


---
⭐ Projeyi beğendiyseniz star vermeyi unutmayın!
