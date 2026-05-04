from locust import HttpUser, task, between
import uuid

class CircleGuardUser(HttpUser):
    wait_time = between(1, 5)

    @task(3)
    def login_flow(self):
        self.client.post("/auth/login", json={
            "username": "user_test",
            "password": "password123"
        })

    @task(2)
    def view_profile(self):
        self.client.get("/identity/profile")

    @task(1)
    def submit_form(self):
        self.client.post("/form/submit", json={
            "id": str(uuid.uuid4()),
            "type": "AUDIT",
            "data": "Performance Test Data"
        })

    @task(2)
    def check_notifications(self):
        self.client.get("/notification/user/test-id")

    @task(1)
    def dashboard_summary(self):
        self.client.get("/dashboard/summary")
