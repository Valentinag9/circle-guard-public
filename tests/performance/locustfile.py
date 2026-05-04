from locust import HttpUser, task, between

class CircleGuardUser(HttpUser):
    wait_time = between(1, 3)

    @task(3)
    def login_scenario(self):
        """Escenario 1: Login de usuario"""
        self.client.post("/auth/login", json={
            "username": "student_test",
            "password": "password123"
        }, headers={"Content-Type": "application/json"})

    @task(2)
    def report_symptoms(self):
        """Escenario 2: Reporte de síntomas (Form Service)"""
        self.client.post("/forms/submit", json={
            "anonymousId": "anon-uuid-123",
            "hasSymptoms": True,
            "symptoms": ["fever", "cough"]
        }, headers={"Content-Type": "application/json"})

    @task(1)
    def check_status(self):
        """Escenario 3: Consulta de estado en Gateway"""
        self.client.get("/gateway/validate/anon-uuid-123")
